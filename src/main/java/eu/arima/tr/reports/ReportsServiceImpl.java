package eu.arima.tr.reports;

import eu.arima.tr.workLogs.Worklog;
import eu.arima.tr.workLogs.WorklogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static eu.arima.tr.reports.DayStatus.*;

@Service
class ReportsServiceImpl implements ReportsService {

	private final WorklogRepository worklogRepository;

	public ReportsServiceImpl(WorklogRepository worklogRepository) {
		this.worklogRepository = worklogRepository;
	}

	@Override
	public DayStatusSummary getDayStatusSummaryForWorkerAndDay(String workerUserName, LocalDate date) {

		List<Worklog> worklogsForDay = this.worklogRepository.findByUsernameAndDate(workerUserName, date);

		int totalDuration = worklogsForDay.stream()
				.mapToInt(Worklog::getDuration)
				.sum();

		DayStatusSummary status = new DayStatusSummary(date, workerUserName);

		if (totalDuration == 8) {
			status.addDayStatus(RIGHT_HOURS);
		} else if (totalDuration > 8) {
			status.addDayStatus(EXTRA_HOURS);
		} else {
			status.addDayStatus(MISSING_HOURS);
		}

		return status;
	}

}
