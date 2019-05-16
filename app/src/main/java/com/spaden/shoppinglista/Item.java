package com.spaden.shoppinglista;

public class Item {

    private String mToBuy;
    private String mQty;
    private Boolean Checked = false;

    public String getQty(){
        return mQty;
    }
    public String getToBuy(){
        return mToBuy;
    }

    public void setToBuy(String mToBuy) {this.mToBuy = mToBuy;}
    public void setQty(String mQty) {this.mQty = mQty;}

    public Boolean getChecked() {
        return Checked;
    }

    public void setChecked(Boolean checked) {
        Checked = checked;
    }
}
