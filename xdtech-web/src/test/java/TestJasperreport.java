//import java.util.HashMap;
//
//import net.sf.jasperreports.engine.JREmptyDataSource;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//
///*
// * Copyright 2013-2014 the original author or authors.
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
///**
// * 
// * @author max.zheng
// * @create 2014-11-18下午9:43:21
// * @since 1.0
// * @see
// */
//public class TestJasperreport {
//
//	/**
//	 * 
//	 * @author max.zheng
//	 * @create 2014-11-18下午9:43:21
//	 * @modified by
//	 * @param args
//	 * @throws JRException
//	 */
//	public static void main(String[] args) throws JRException {
//		JasperReport jasperReport;
//		JasperPrint jasperPrint;
//		jasperReport = JasperCompileManager
//				.compileReport("F:/jasperreport/report3.jrxml");
//		jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(),
//				new JREmptyDataSource());
//		JasperExportManager.exportReportToPdfFile(jasperPrint,
//				"F:/jasperreport/results/report3.pdf");
//
//	}
//
//}
