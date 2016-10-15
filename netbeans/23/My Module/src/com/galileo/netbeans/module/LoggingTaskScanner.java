package com.galileo.netbeans.module;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.netbeans.spi.tasklist.FileTaskScanner;
import org.netbeans.spi.tasklist.Task;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/* @todo insert class description */
public class LoggingTaskScanner extends FileTaskScanner {
    
   private static final String GROUP_NAME = "logging-tasklist";

   private static final String[] TOKENS = {
      "System.out.println", 
      "System.err.println", 
      "printStackTrace"};
    
   private Pattern  regexp   = null;
   private Callback callback = null;

   public LoggingTaskScanner(String name, String desc) {
       super(name, name, null);
   }

   public static LoggingTaskScanner create() {
       System.out.println("create");
       String name = NbBundle.getBundle(LoggingTaskScanner.class).getString("LBL_loggingtask");
       String desc = NbBundle.getBundle(LoggingTaskScanner.class).getString("HINT_loggingtask");
       return new LoggingTaskScanner(name, desc);
   }

   public List<? extends Task> scan(FileObject file) {
      System.out.println("scan");

      List<Task> tasks = new LinkedList<Task>();

      int lineno = 0;

      try {
         for (String line : file.asLines()) {
            lineno++;
            
            Matcher matcher = getScanRegexp().matcher(line);
            
            if (matcher.find()) {
               String description = line.subSequence(matcher.start()+1, line.length()).toString();

               Task task = Task.create( file, GROUP_NAME, description, lineno );
               tasks.add(task);
            }
         }
      } catch (IOException ex) {
         Exceptions.printStackTrace(ex);
      }

      return tasks;
   }

   private Pattern getScanRegexp() {
        if (regexp == null) {
            StringBuilder sb = new StringBuilder(200);
            //Collection<String> patterns = Settings.getDefault().getPatterns();
            boolean needSeparator = false;
            for( String s : TOKENS) {
                if( needSeparator ) {
                    sb.append('|');
                }
                needSeparator = true;
                int n = s.length();

                if (Character.isJavaIdentifierPart(s.charAt(0))) {
                    sb.append("\\W"); // NOI18N
                }

                for (int j = 0; j < n; j++) {
                    char c = s.charAt(j);

                    if ((c == '(') || (c == ')') ||
                        (c == '{') || (c == '}') ||
                        (c == '[') || (c == ']') ||
                        (c == '?') || (c == '*') || (c == '+') ||
                        (c == '!') || (c == '|') || (c == '\\') ||
                        (c == '^') || (c == '$')) {
                        sb.append('\\');
                    }
                    sb.append(c);
                }
                if (Character.isJavaIdentifierPart(s.charAt(n-1))) {
                    sb.append("\\b");
                }
            }
            try {
                regexp = Pattern.compile(sb.toString());
            } catch (PatternSyntaxException e) {
                Logger.getLogger(getClass().getName()).log(Level.INFO, null, e);
                return null;
            }
        }
        return regexp;
    }
    
    public void attach(Callback callback) {
       System.out.println("attach");
       if( null == callback && null != this.callback ) {
          regexp = null;
          //Settings.getDefault().removePropertyChangeListener( this );
       } else if( null != callback && null == this.callback ) {
          //Settings.getDefault().addPropertyChangeListener( this );
       }
       this.callback = callback;
    }
    
    @Override
    public void notifyPrepare() {
       System.out.println("notifyPrepare");
       getScanRegexp();
    }

    /** @todo add description */
    @Override
    public void notifyFinish() {
       System.out.println("notifyFinish");
       regexp = null;
    }
}
