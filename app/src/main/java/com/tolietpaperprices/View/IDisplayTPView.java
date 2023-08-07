package com.tolietpaperprices.View;

public interface IDisplayTPView {
    interface Listener {
        public void editPackageButton(int index);

        public void deletePackageButton(int index);
    }
}
