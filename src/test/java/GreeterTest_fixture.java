import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(Enclosed.class)
public class GreeterTest_fixture {

	@RunWith(JUnit4.class)
	public static class 挨拶のテスト_5時以上12時未満の場合_おはようございますと返す{
		private final String expectMroningGreet = "おはようございます";
		@Test
		public void 五時丁度の場合() throws Exception {
			testGreet(time(5, 0, 0, 0), expectMroningGreet);
		}
		@Test
		public void 五時1ミリ秒の場合() throws Exception {
			testGreet(time(5, 0, 0, 1), expectMroningGreet);
		}
		@Test
		public void 十二時2ミリ秒前の場合() throws Exception {
			testGreet(time(11, 59, 59, 998), expectMroningGreet);
		}
		@Test
		public void 十二時1ミリ秒前の場合() throws Exception {
			testGreet(time(11, 59, 59, 999), expectMroningGreet);
		}
	}

	@RunWith(JUnit4.class)
	public static class 挨拶のテスト_12時以上18時未満の場合_こんにちはと返す{
		final String expectDayTimeGreet = "こんにちは";
		@Test
		public void 十二時丁度の場合() throws Exception {
			testGreet(time(12, 0, 0, 0), expectDayTimeGreet);
		}
		@Test
		public void 十二時1ミリ秒の場合() throws Exception {
			testGreet(time(12, 0, 0, 1), expectDayTimeGreet);
		}
		@Test
		public void 十八時2ミリ秒前の場合() throws Exception {
			testGreet(time(17, 59, 59, 998), expectDayTimeGreet);
		}
		@Test
		public void 十八時1ミリ秒前の場合() throws Exception {
			testGreet(time(17, 59, 59, 999), expectDayTimeGreet);
		}
	}
	
	@RunWith(JUnit4.class)
	public static class 挨拶のテスト_18時以上5時未満の場合_こんばんはと返す{
		private String expectEveningGreet = "こんばんは";
		@Test
		public void 十八時丁度の場合() throws Exception {
			testGreet(time(18, 0, 0, 0), expectEveningGreet);
		}
		@Test
		public void 十八時1ミリ秒の場合() throws Exception {
			testGreet(time(18, 0, 0, 1), expectEveningGreet);
		}
		@Test
		public void 五時2ミリ秒前の場合() throws Exception {
			testGreet(time(4, 59, 59, 998), expectEveningGreet);;
		}
		@Test
		public void 五時1ミリ秒前の場合() throws Exception {
			testGreet(time(4, 59, 59, 999), expectEveningGreet);
		}
	}

	private static void testGreet(final Date date, String expectedGreet) {
		//準備
		IAppDate appDateMock = new IAppDate() {
			@Override public Date now() { return date; }
		};
		//実行
		String resultGreet = new Greeter(appDateMock).greet();
		//検証
		assertThat("date="+format(date),resultGreet,is(expectedGreet));
	}

	private static Date time(int hour, int minitus, int sec, int milSec) {
		Calendar calender = Calendar.getInstance();
		calender.set(Calendar.HOUR_OF_DAY,hour);
		calender.set(Calendar.MINUTE,minitus);
		calender.set(Calendar.SECOND,sec);
		calender.set(Calendar.MILLISECOND,milSec);
		return calender.getTime();
	}

	private static String format(final Date now) {
		return new SimpleDateFormat("HHmmssSSS").format(now);
	}
}