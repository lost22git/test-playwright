package lost.test.playwright;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.Proxy;

import java.util.concurrent.locks.LockSupport;

// 设置 env 避免自动下载各种浏览器
// set PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1
public class Main {

    public static void main(String[] args) {
        var proxy = new Proxy("http://localhost:55556");
        var launchOptions = new BrowserType.LaunchOptions()
            .setChannel("msedge")
//            .setHeadless(true)
            .setProxy(proxy)
            .setDevtools(true);

        try (var playwright = Playwright.create();
             var browser = playwright.chromium().launch(launchOptions);
        ) {
            var uri = "https://www.google.com";
            var page = browser.newPage();
            page.navigate(uri);
            System.out.println(page.title());

            LockSupport.park();
        }
    }
}
