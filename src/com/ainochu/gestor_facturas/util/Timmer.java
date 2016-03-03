package com.ainochu.gestor_facturas.util;


import com.ainochu.gestor_facturas.bean.TableClientes;

import java.util.Date;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ainoa on 06/12/2015.
 */
public class Timmer extends Observable {
    private TimerTask timerTask;

    /**
         * Lanza un timer cada segundo.
         */
        public Timmer()
        {
            timerTask = new TimerTask()
            {
                public void run()
                {
                    setChanged();
                    notifyObservers(new Date());
                }
            };
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(timerTask,0, 30000);
        }


}
