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

import java.io.File;
import java.io.Serializable;
import java.net.URI;

/**
 * The Class ModifiedTimeSortableFile extends java.io.File class and
 * implements Comparable to sort files list based upon their modified date
 */
class ModifiedTimeSortableFile extends File implements Serializable, Comparable<File>
{
	private static final long serialVersionUID = 1373373728209668895L;

	public ModifiedTimeSortableFile(String parent, String child) {
		super(parent, child);
	}


	public ModifiedTimeSortableFile(URI uri) {
		super(uri);
	}


	public ModifiedTimeSortableFile(File parent, String child) {
		super(parent, child);
	}


	public ModifiedTimeSortableFile(String string) {
		super(string);
	}

	public int compareTo(File anotherPathName) {
		long thisVal = this.lastModified();
		long anotherVal = anotherPathName.lastModified();
		return (thisVal<anotherVal ? -1 : (thisVal==anotherVal ? 0 : 1));
	}
}