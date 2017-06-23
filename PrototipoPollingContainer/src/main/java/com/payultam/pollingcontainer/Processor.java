package com.payultam.pollingcontainer;

import java.util.logging.Logger;

import org.openspaces.core.GigaSpace;
import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.polling.Polling;
import org.springframework.stereotype.Service;

import com.payulatam.model.Movement;


/**
 * The processor simulates work done no un-processed Data object. The processData
 * accepts a Data object, simulate work by sleeping, and then sets the processed
 * flag to true and returns the processed Data.
 */
@EventDriven
@Polling
@Service
public class Processor {

    Logger log= Logger.getLogger(this.getClass().getName());
    
    public Processor() {
		System.out.println(this.getClass() + " constructed");
	}
    
    @EventTemplate
    public Movement getTemplate() {
    	Movement mov = new Movement();
    	mov.setProcessed(false);
    	return mov;
    }


    /**
     * Process the given Data object and returning the processed Data.
     *
     * Can be invoked using OpenSpaces Events when a matching event
     * occurs.
     */
    @SpaceDataEvent
    public Movement processData(Movement data, GigaSpace space) {
        // sleep to simulate some work
        data.setProcessed(true);
//        data.setData("PROCESSED : " + data.getRawData());
        System.out.println("PollingContainer : " + data.getId());
        log.info(" ------ PROCESSED : " + data);
        return data;
    }

}
