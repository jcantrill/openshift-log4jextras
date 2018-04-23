/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.openshift.log4jextras.appenders;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

public class DailyRollingFileAppenderTest {
	
    private static org.apache.log4j.Logger log = Logger.getLogger(DailyRollingFileAppenderTest.class);
    private static final int MINUTES = 3;
	private File dir;
	
    @Before
    public void setup(){
        dir = new File("target/testfiles");
        if(dir.exists()) {
            dir.delete();
        }
    }

    @Test
	public void testAppender() throws Exception{
        final URL url = DailyRollingFileAppenderTest.class.getClassLoader().getResource("log4j.xml");
        PropertyConfigurator.configure(url);

        final int total = 60 * 1000 * MINUTES;
        System.out.println(String.format("Testing max settings. This test takes up to %d minutes to verify", MINUTES));
        final long expire = System.currentTimeMillis() + total;
        while(System.currentTimeMillis() < expire) {
            log.trace("Trace");
            log.debug("Debug");
            log.info("Info");
            log.warn("Warn");
            log.error("Error");
            log.fatal("Fatal");
            Thread.sleep(20 * 1000);
        }
        
        String[] testFiles = dir.list(new FilenameFilter() {
            
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("test-agent.log");
            }
        });
        assertEquals("Exp no more then 2 backups", 3, testFiles.length);
	}

}
