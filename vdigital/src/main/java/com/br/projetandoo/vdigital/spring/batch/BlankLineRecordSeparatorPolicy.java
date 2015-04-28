package com.br.projetandoo.vdigital.spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.separator.SimpleRecordSeparatorPolicy;

public class BlankLineRecordSeparatorPolicy extends SimpleRecordSeparatorPolicy {
	
	private static final Logger LOG = LoggerFactory.getLogger(BlankLineRecordSeparatorPolicy.class);
	
	@Override
    public boolean isEndOfRecord(final String line) {
        if (line.trim().length() == 0) {
        	LOG.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&   ENTROU NO IF IS_END_OF_RECORD");
            return false;
        }
        return super.isEndOfRecord(line);
    }
 
    @Override
    public String postProcess(final String record) {
        if (record == null || record.trim().length() == 0) {
        	
        LOG.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&   ENTROU NO IF POSTPROCESS");
        	
            return null;
        }
        
        return super.postProcess(record);
    }
    
}
