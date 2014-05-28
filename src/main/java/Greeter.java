import java.text.SimpleDateFormat;


public class Greeter {
	private IAppDate date;
	final int morningStart = 50000000;
	final int dayTimeStart = 120000000;	
	final int eveningStart = 180000000;	
	
	public Greeter(IAppDate date) {
		this.date = date;
	}

	public String greet() {
		if(isMorning()){
			return "おはようございます";
		}else if(isDayTime()){
			return "こんにちは";
		}else{
			return "こんばんは";			
		}
	}

	private boolean isMorning() {
		return between(morningStart,dayTimeStart, nowTime());
	}

	private boolean isDayTime() {
		return between(dayTimeStart,eveningStart, nowTime());
	}

	private boolean between(int start, int end, Integer nowTime) {
		return start <= nowTime && nowTime < end;
	}

	private Integer nowTime() {
		return new Integer(new SimpleDateFormat("HHmmssSSS").format(date.now()));
	}
}
