import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class GreeterTest {
	
	@Test
	public void 挨拶のテスト_5時以上12時未満の場合_おはようございますと返す() throws Exception {
		String expectGreet = "おはようございます";
		//1テストメソッド複数アサートは推奨されていませんがやっちゃってます。
		testGreet(time(5, 0, 0, 0), expectGreet);
		testGreet(time(5, 0, 0, 1), expectGreet);
		testGreet(time(11, 59, 59, 998), expectGreet);
		testGreet(time(11, 59, 59, 999), expectGreet);
	}

	@Test
	public void 挨拶のテスト_12時以上18時未満の場合_こんにちはと返す() throws Exception {
		String expectGreet = "こんにちは";
		testGreet(time(12, 0, 0, 0), expectGreet);
		testGreet(time(12, 0, 0, 1), expectGreet);
		testGreet(time(17, 59, 59, 998), expectGreet);
		testGreet(time(17, 59, 59, 999), expectGreet);
	}

	@Test
	public void 挨拶のテスト_18時以上5時未満の場合_こんばんはと返す() throws Exception {
		String expectGreet = "こんばんは";
		testGreet(time(18, 0, 0, 0), expectGreet);
		testGreet(time(18, 0, 0, 1), expectGreet);
		testGreet(time(4, 59, 59, 998), expectGreet);;
		testGreet(time(4, 59, 59, 999), expectGreet);
	}

	private void testGreet(final Date date, String expectedGreet) {
		//準備
		IAppDate appDate = new IAppDate() {
			@Override public Date now() { return date; }
		};
		//実行
		String greet = new Greeter(appDate).greet();
		//検証
		assertThat("date="+format(date),greet,is(expectedGreet));
	}

	private Date time(int hour, int minitus, int sec, int milSec) {
		Calendar calender = Calendar.getInstance();
		calender.set(Calendar.HOUR_OF_DAY,hour);
		calender.set(Calendar.MINUTE,minitus);
		calender.set(Calendar.SECOND,sec);
		calender.set(Calendar.MILLISECOND,milSec);
		return calender.getTime();
	}

	private String format(final Date now) {
		return new SimpleDateFormat("HHmmssSSS").format(now);
	}
}