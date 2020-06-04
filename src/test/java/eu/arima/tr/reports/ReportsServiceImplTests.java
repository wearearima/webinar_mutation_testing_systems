package eu.arima.tr.reports;

import static eu.arima.tr.reports.DayStatus.RIGHT_HOURS;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import eu.arima.tr.workLogs.Worklog;
import eu.arima.tr.workLogs.WorklogRepository;

@ExtendWith(MockitoExtension.class)
public class ReportsServiceImplTests {

	private static final LocalDate DIA = LocalDate.now();
	private static final String USU = "JESSI";

	@InjectMocks
	ReportsServiceImpl reportsService;

	@Mock
	WorklogRepository worklogRepository;

	@Test
	public void metodo_funciona() {
		reportsService.getDayStatusSummaryForWorkerAndDay(USU, DIA);
		assertTrue(true);
	}

	@Test
	public void recupero_los_partes_del_usuario_y_dia() {
		when(worklogRepository.findByUsernameAndDate(USU, DIA)).thenReturn(emptyList());
		reportsService.getDayStatusSummaryForWorkerAndDay(USU, DIA);
		verify(worklogRepository).findByUsernameAndDate(USU, DIA);
	}

	@Test
	public void si_la_suma_de_duraciones_es_8_el_status_es_RIGHT_HOURS() {
		Worklog worklog = mock(Worklog.class);
		when(worklog.getDuration()).thenReturn(8);
		List<Worklog> partes = Arrays.asList(worklog);
		when(worklogRepository.findByUsernameAndDate(USU, DIA)).thenReturn(partes);
		DayStatusSummary result = reportsService.getDayStatusSummaryForWorkerAndDay(USU, DIA);
		assertEquals(RIGHT_HOURS, result.getStatusList().get(0));
	}

}
