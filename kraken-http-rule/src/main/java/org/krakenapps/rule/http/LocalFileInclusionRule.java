/*
 * Copyright 2011 NCHOVY
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.krakenapps.rule.http;

import java.util.Map;

public class LocalFileInclusionRule extends HttpRequestRule {
	private Map<String, String> params;

	public LocalFileInclusionRule(String id, String msg, String path, Map<String, String> params) {
		super("lfi", id, msg, path);
		this.params = params;
	}

	@Override
	public boolean match(HttpRequestContext c) {
		for (String var : params.keySet()) {
			String value = c.getParameters().get(var);

			if (params.get(var) == null) {
				if (value == null)
					return false;

			} else {
				String expected = params.get(var);
				if (value == null)
					return false;

				if (!expected.matches("(\\.\\./)*" + value))
					return false;
			}

			if (!value.contains("%00"))
				return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return getId() + " " + getMessage();
	}

}
