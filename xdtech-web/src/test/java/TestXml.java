import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.xdtech.sys.model.CodeValue;
import com.xdtech.sys.model.DictionaryCode;

/*
 * Copyright 2013-2014 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 
 * @author max.zheng
 * @create 2014-9-29下午11:03:47
 * @since 1.0
 * @see
 */
public class TestXml {
	public static void main(String[] args) {
		System.out.println(TestXml.class.getResource("/").getPath());
		List<DictionaryCode> dictionaryCodes = new ArrayList<DictionaryCode>();
		DictionaryCode dictionaryCode = new DictionaryCode();
		dictionaryCode.setId(1L);
		dictionaryCode.setKey("SEX");
		dictionaryCode.setCreateTime(new Date());
		dictionaryCode.setName("性别");
		
		CodeValue c1 = new CodeValue();
		c1.setId(1L);
		c1.setValue("M");
		c1.setName("男");
		dictionaryCode.getCodeValues().add(c1);
		dictionaryCodes.add(dictionaryCode);
        XStream xstream = new XStream( ); 
        //xstream.alias("policy", Policy.class);
        //xstream.alias("item", Item.class);
        String xml = xstream.toXML(dictionaryCodes); 
        System.out.println(xml);
        
//        DictionaryCode newPolicy = (DictionaryCode)xstream.fromXML(xml); 
//        System.out.println(newPolicy.getCodeValues().toString());
//        System.out.println(xstream.toXML(newPolicy));
        List<DictionaryCode> tempDictionaryCodes = (List<DictionaryCode>) xstream.fromXML(new File("F:/GitHub/xdtech-sys/xdtech-web/src/test/java/DictionaryCode.xml"));
        System.out.println(tempDictionaryCodes);
        for (DictionaryCode dictionaryCode2 : tempDictionaryCodes) {
			System.out.println(dictionaryCode2.getId()+","+dictionaryCode2.getName()+","+dictionaryCode2.getKey());
			System.out.println(dictionaryCode2.getCodeValues().size());
		}
        
	}
}
