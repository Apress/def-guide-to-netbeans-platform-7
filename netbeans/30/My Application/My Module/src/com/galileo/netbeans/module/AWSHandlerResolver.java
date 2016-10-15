package com.galileo.netbeans.module;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.PortInfo;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class AWSHandlerResolver implements HandlerResolver {

   private String awsSecretKey;

   public AWSHandlerResolver(String awsSecretKey) {
      this.awsSecretKey = awsSecretKey;
   }

   @SuppressWarnings("unchecked")
   @Override
   public List<Handler> getHandlerChain(PortInfo portInfo) {
      List<Handler> handlerChain = new ArrayList<Handler>();
      handlerChain.add(new AWSHandler(awsSecretKey));

      return handlerChain;
   }

   private static class AWSHandler implements SOAPHandler<SOAPMessageContext> {

      private byte[] secretBytes;

      public AWSHandler(String awsSecretKey) {
         secretBytes = awsSecretKey.getBytes();
      }

      public void close(MessageContext messagecontext) {
      }

      public Set<QName> getHeaders() {
         return null;
      }

      public boolean handleFault(SOAPMessageContext messagecontext) {
         return true;
      }

      public boolean handleMessage(SOAPMessageContext messagecontext) {
         Boolean outbound = (Boolean) messagecontext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
         if (outbound) {
            try {
               SOAPMessage soapMessage = messagecontext.getMessage();
               SOAPBody soapBody = soapMessage.getSOAPBody();
               Node firstChild = soapBody.getFirstChild();

               String timeStamp = getTimestamp();
               String signature = getSignature(firstChild.getLocalName(), timeStamp, secretBytes);

               appendTextElement(firstChild, "Signature", signature);
               appendTextElement(firstChild, "Timestamp", timeStamp);
            } catch (SOAPException se) {
               throw new RuntimeException("SOAPException was thrown.", se);
            }
         }
         return true;
      }

      private String getSignature(String operation, String timeStamp, byte[] secretBytes) {
         try {
            String toSign = operation + timeStamp;

            Mac signer = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(secretBytes, "HmacSHA256");

            signer.init(keySpec);
            signer.update(toSign.getBytes());
            byte[] signBytes = signer.doFinal();

            String signature = new String(Base64.encodeBase64(signBytes));
            return signature;
         } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException("NoSuchAlgorithmException was thrown.", nsae);
         } catch (InvalidKeyException ike) {
            throw new RuntimeException("InvalidKeyException was thrown.", ike);
         }
      }

      private String getTimestamp() {
         Calendar calendar = Calendar.getInstance();
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
         dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
         return dateFormat.format(calendar.getTime());
      }


      private void appendTextElement(Node node, String elementName, String elementText) {
         Element element = node.getOwnerDocument().createElement(elementName);
         element.setTextContent(elementText);
         node.appendChild(element);
      }
   }
}
