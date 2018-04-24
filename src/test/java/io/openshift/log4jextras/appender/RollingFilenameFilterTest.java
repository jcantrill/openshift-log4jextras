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

package io.openshift.log4jextras.appender;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FilenameFilter;

import org.junit.Test;

public class RollingFilenameFilterTest {

    private File appenderFile = new File("target/testfiles/test-agent.log");
    private FilenameFilter filter = new RollingFilenameFilter(appenderFile.getPath());
    
    @Test
    public void testShouldNotAcceptFileThatDoNoHaveSamePrefix() {
        String file = "a";
        assertFalse(filter.accept(appenderFile, file));
    }

    @Test
    public void testShouldAcceptFileThatHasSamePrefix() {
        String file = "test-agent.log_2018-04-23-20-29";
        assertTrue(filter.accept(appenderFile, file));
    }

}
