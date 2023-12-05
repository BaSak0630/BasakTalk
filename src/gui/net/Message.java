package gui.net;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter@Setter
public class Message {
    String dateTime;
    String to;
    String from;
    String msg;

    public Message(String dateTime, String to, String from, String msg) {
        this.dateTime = dateTime;
        this.to = to;
        this.from = from;
        this.msg = msg;
    }

    @Override
    public String toString() {
        String s = dateTime+"/"+to+"/"+from+"/"+msg;
        return s;
    }
}
