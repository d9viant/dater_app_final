package com.comtrade.controllerBL;

import com.comtrade.domain.User;
import com.comtrade.profil.SO.SaveUserSO;
import com.comtrade.sysops.GeneralSystemOperation;

public class ControllerBLogic {

    private static ControllerBLogic instance;
    private ControllerBLogic(){

    }

    public static ControllerBLogic getInstance(){
        if(instance == null){
            instance=new ControllerBLogic();
        }
        return instance;
    }

    public void saveProfile(User u){
        GeneralSystemOperation op = new SaveUserSO();
        op.executeSo(u);
    }

    public void checkProfile(Boolean check){

    }

}
