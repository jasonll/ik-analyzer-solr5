/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wltea.analyzer.lucene;

import java.io.IOException;
import java.util.Map;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.ResourceLoader;
import org.apache.lucene.util.AttributeFactory;
import org.wltea.analyzer.dic.Dictionary;

/**
 * @author <a href="mailto:su.eugene@gmail.com">Eugene Su</a>
 */
public class IKTokenizerFactory extends ReloadableTokenizerFactory{
  private boolean useSmart;
	
  public boolean useSmart() {
	return useSmart;
  }
	
  public void setUseSmart(boolean useSmart) {
    this.useSmart = useSmart;
  }
	
  public IKTokenizerFactory(Map<String,String> args) {
    super(args);
    String  useSmartArg = args.get("useSmart");
    this.setUseSmart(useSmartArg != null ? Boolean.parseBoolean(useSmartArg) : false);
    System.out.println(":::ik:construction::::::::::::::::::::::::::" + conf);
  }
  
  @Override
  public Tokenizer create(AttributeFactory factory) {
    Tokenizer _IKTokenizer = new IKTokenizer(factory , this.useSmart);
    return _IKTokenizer;
  }

@Override
public void inform(ResourceLoader loader) throws IOException {
	// TODO Auto-generated method stub
	System.out.println(":::ik:::inform::::::::::::::::::::::::" + conf);
	ReloaderRegister.register(this, loader, conf);
	
}

@Override
public void update() {
	// TODO Auto-generated method stub
	Dictionary.addDic2MainDic();
}
  
}

