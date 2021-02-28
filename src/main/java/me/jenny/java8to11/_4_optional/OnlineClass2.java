package me.jenny.java8to11._4_optional;

import java.util.Optional;

public class OnlineClass2 {
    private Integer id;
    private String title;
    private boolean closed;
    private Progress progress;

    public OnlineClass2(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /*
    public Progress getProgress() {
        // 1. 이렇게 NPE 대신 명확한 에러를 던져준다. 이 방법의 문제:
        // - Runtime exception 을 던지면 이 코드를 쓰는 Client 쪽 코드가 고통이 덜하지만,
        //   Checked exception 을 던지면 에러 처리를 감지해야 한다.
        // - 에러가 발생되면 자바는 StackTrace 를 찍는데(에러가 발생하기 전까지 어떤 call stack 을 거쳐 발생하였는지)
        //   정보를 담게 된다. 이 자체로 리소스를 쓰게 되므로 진짜 필요한 경우에만 예외를 써야지, 로직 처리용으로 에러쓰면 안된다.
        if (this.progress == null) {
            throw new IllegalStateException();
        }
        // 2. null 을 리턴하고 Client 가 null 체크하고 처리한다.
        return progress;
    }
    */

    // 자바 8 부터 Optional 이라는 선택지가 생겼다. 리턴 타입에만 쓰는게 좋다.
    // primitive type 은 Optional 안에 넣고 쓰면 boxing/unboxing 이 일어나서 성능이 더 안좋아진다.(OptionalInt 쓰자)
    public Optional<Progress> getProgress() {
        return Optional.ofNullable(this.progress);
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
}
