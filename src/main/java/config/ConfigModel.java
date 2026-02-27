package config;

public class ConfigModel {

    private Framework framework;
    private Timeouts timeouts;

    // ===== GETTERS & SETTERS =====

    public void setFramework(Framework framework) {
        this.framework = framework;
    }

    public void setTimeouts(Timeouts timeouts) {
        this.timeouts = timeouts;
    }

    public Framework getFramework() {
        return framework;
    }

    public Timeouts getTimeouts() {
        return timeouts;
    }

    // =============================
    // FRAMEWORK SECTION
    // =============================
    public static class Framework {

        private String browser;          // default browser
        private String baseUrl;
        private int implicitWait;
        private boolean headless;        // ✅ Added
        private int pageLoadTimeout;     // ✅ Added

        // ===== SETTERS =====

        public void setBrowser(String browser) {
            this.browser = browser;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public void setImplicitWait(int implicitWait) {
            this.implicitWait = implicitWait;
        }

        public void setHeadless(boolean headless) {   // ✅ Added
            this.headless = headless;
        }

        public void setPageLoadTimeout(int pageLoadTimeout) {  // ✅ Added
            this.pageLoadTimeout = pageLoadTimeout;
        }

        // ===== GETTERS =====

        public String getBrowser() {          // 🔥 Use this instead of getDefaultBrowser
            return browser;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public int getImplicitWait() {
            return implicitWait;
        }

        public boolean isHeadless() {        // ✅ Added
            return headless;
        }

        public int getPageLoadTimeout() {    // ✅ Added
            return pageLoadTimeout;
        }
    }

    // =============================
    // TIMEOUTS SECTION
    // =============================
    public static class Timeouts {

        private int pageLoad;
        private int script;

        public void setPageLoad(int pageLoad) {
            this.pageLoad = pageLoad;
        }

        public void setScript(int script) {
            this.script = script;
        }

        public int getPageLoad() {
            return pageLoad;
        }

        public int getScript() {
            return script;
        }
    }
}
