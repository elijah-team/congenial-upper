package tripleo.vendor.antlr277.build;

import java.io.*;

/** Simple class that uses build.Tool to compile ANTLR's Java stuff */
public class ANTLR {
    public static String compiler = "javac";
    public static String jarName = "tripleo.vendor.antlr277.jar";
    public static String root = ".";

    public static String[] srcdir = {
		    "antlr",
        "tripleo/vendor/antlr277/actions/cpp",
        "tripleo/vendor/antlr277/actions/java",
        "tripleo/vendor/antlr277/actions/csharp",
        "tripleo/vendor/antlr277/collections",
        "tripleo/vendor/antlr277/collections/impl",
        "tripleo/vendor/antlr277/debug",
        "tripleo/vendor/antlr277/ASdebug",
        "tripleo/vendor/antlr277/debug/misc",
        "tripleo/vendor/antlr277/preprocessor"
    };

    public ANTLR() {
        compiler = System.getProperty("tripleo.vendor.antlr277.build.compiler", compiler);
        root = System.getProperty("tripleo.vendor.antlr277.build.root", root);
    }

    public String getName() {
        return "ANTLR";
    }

    /** Build ANTLR.  action on cmd-line matches method name */
    public void build(Tool tool) {
        if (!rootIsValidANTLRDir(tool)) {
            return;
        }
        // run ANTLR on its own .g files
        tool.antlr(root + "/tripleo/vendor/antlr277/tripleo.vendor.antlr277.g");
        tool.antlr(root + "/tripleo/vendor/antlr277/tokdef.g");
        tool.antlr(root + "/tripleo/vendor/antlr277/preprocessor/preproc.g");
        tool.antlr(root + "/tripleo/vendor/antlr277/actions/java/action.g");
        tool.antlr(root + "/tripleo/vendor/antlr277/actions/cpp/action.g");
        tool.antlr(root + "/tripleo/vendor/antlr277/actions/csharp/action.g");
        for (int i = 0; i < srcdir.length; i++) {
            String cmd = compiler + " -d " + root + " " + root + "/" + srcdir[i] + "/*.java";
            tool.system(cmd);
        }
    }

    /** ANTLR root dir must contain an "antlr" dir and must have java
     *  files underneath etc...
     */
    protected boolean rootIsValidANTLRDir(Tool tool) {
        if (root == null) {
            return false;
        }
        File antlrRootDir = new File(root);
        if (!antlrRootDir.exists()) {
            tool.error("Property tripleo.vendor.antlr277.build.root==" + root + " does not exist");
            return false;
        }
        if (!antlrRootDir.isDirectory()) {
            tool.error("Property tripleo.vendor.antlr277.build.root==" + root + " is not a directory");
            return false;
        }
        String[] antlrDir = antlrRootDir.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return dir.isDirectory() && name.equals("antlr");
            }
        });
        if (antlrDir == null || antlrDir.length == 0) {
            tool.error("Property tripleo.vendor.antlr277.build.root==" + root
                    + " does not appear to be a valid ANTLR project root (no antlr subdir)");
            return false;
        }
        File antlrPackageDir = new File(root + "/antlr");
        String[] antlrPackageJavaFiles = antlrPackageDir.list();
        if (antlrPackageJavaFiles == null || antlrPackageJavaFiles.length == 0) {
            tool.error("Property tripleo.vendor.antlr277.build.root==" + root
                    + " does not appear to be a valid ANTLR project root (no .java files in antlr subdir");
            return false;
        }
        return true;
    }

    /** Jar up all the .class files */
    public void jar(Tool tool) {
        if (!rootIsValidANTLRDir(tool)) {
            return;
        }
        StringBuffer cmd = new StringBuffer(2000);
        cmd.append("jar cvf " + root + "/" + jarName);
        for (int i = 0; i < srcdir.length; i++) {
            cmd.append(" " + root + "/" + srcdir[i] + "/*.class");
        }
        tool.system(cmd.toString());
    }
}
