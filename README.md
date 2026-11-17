# BOSS CALL

A basic Android SIM dialer. It opens the system phone dialer using `ACTION_DIAL`; it does not place calls silently and requests no call permissions.

Caller-ID hiding is carrier/network dependent. The optional `#31#` prefix is only a network-dependent attempt, not a guarantee or spoofing feature. Internet app-to-app calling is not implemented in this first build.

GitHub Actions builds a debug APK on pushes to `main` or via **Actions → Build BOSS CALL APK → Run workflow**. Download the `BOSS-CALL-debug-apk` artifact from the completed run.
