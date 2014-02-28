package Server;
public class OSDetectorServer
/**
 * This class is for checking the operating system
 * on the running machine, because the reading of the file
 * is different on OS X, Windows and Linux 
 */
{
    private static boolean isWindows = false;
    private static boolean isLinux = false;
    private static boolean isMac = false;

    static
    {
        String os = System.getProperty("os.name").toLowerCase();
        isWindows = os.contains("win");
        isLinux = os.contains("nux") || os.contains("nix");
        isMac = os.contains("mac");
    }

    /**
     * return true for the OS your computer is running
     * @return
     */
    public static boolean isWindows() {	return isWindows;}
    public static boolean isLinux() { return isLinux; }
    public static boolean isMac() { return isMac; };

}