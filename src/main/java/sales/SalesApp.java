package sales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SalesApp {

	public void generateSalesActivityReport(String salesId, int maxRow, boolean isNatTrade, boolean isSupervisor) {

		SalesDao salesDao = new SalesDao();
		SalesReportDao salesReportDao = new SalesReportDao();
		List<String> headers = null;

		List<SalesReportData> filteredReportDataList = new ArrayList<SalesReportData>();

		if (salesId == null) {
			return;
		}

		Sales sales = salesDao.getSalesBySalesId(salesId);

		if (checkEffective(sales))
			return;

		List<SalesReportData> reportDataList = salesReportDao.getReportData(sales);

		for (SalesReportData data : reportDataList) {
			if ("SalesActivity".equalsIgnoreCase(data.getType())) {
				if (data.isConfidential()) {
					if (isSupervisor) {
						filteredReportDataList.add(data);
					}
				}else {
					filteredReportDataList.add(data);
				}
			}
		}

		List<SalesReportData> tempList = new ArrayList<SalesReportData>();
		for (int i=0; i < reportDataList.size() || i < maxRow; i++) {
			tempList.add(reportDataList.get(i));
		}
		filteredReportDataList = tempList;

		if (isNatTrade) {
			headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Time");
		} else {
			headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Local Time");
		}

		SalesActivityReport report = this.generateReport(headers, reportDataList);

		EcmService ecmService = new EcmService();
		ecmService.uploadDocument(report.toXml());

	}

	private boolean checkEffective(Sales sales) {
		Date today = new Date();
		return today.after(sales.getEffectiveTo())
				|| today.before(sales.getEffectiveFrom());
	}

	private SalesActivityReport generateReport(List<String> headers, List<SalesReportData> reportDataList) {
		// TODO Auto-generated method stub
		return null;
	}

}
