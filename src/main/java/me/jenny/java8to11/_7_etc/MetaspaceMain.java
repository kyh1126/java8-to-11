package me.jenny.java8to11._7_etc;

public class MetaspaceMain {
    public static void main(String[] args) {
        // 기존에는 OS 에서 제공하는 native 메모리 중에서 Heap 영역이 있고, 여기에
        // - Eden 영역이 있고 (YoungGen 이 살고)
        // - Old 영역이 있고
        // - PermGen 이 있다.(커봤자 OS에 따라서 64~128 MB 크기가 디폴트로 정해진다)
        //  : 동적으로 클래스를 많이 생성하고 사용하면, 여기에 데이터가 많이 쌓이게 된다. GC 를 해도 넘치면 OOM 난다.

        // PermGen
        // - permanent generation, 클래스 메타데이터를 담는 곳.
        // - Heap 영역에 속함.
        // - 기본값으로 제한된 크기를 가지고 있음.
        // - -XX:PermSize=N, PermGen 초기 사이즈 설정
        // - -XX:MaxPermSize=N, PermGen 최대 사이즈 설정

        // 자바 8 부터 JVM 의 여러 메모리 영역 중에 PermGen 메모리 영역이 없어지고 Metaspace 영역이 생겼다.

        // Metaspace
        // - 클래스 메타데이터를 담는 곳.
        // - Heap 영역이 아니라, Native 메모리 영역이다.
        // - 기본값으로 제한된 크기를 가지고 있지 않다.(64MB 로 시작해서 필요한 만큼 계속 늘어난다)
        // - 자바 8부터는 PermGen 관련 java 옵션은 무시한다.
        // - -XX:MetaspaceSize=N, Metaspace 초기 사이즈 설정.
        // - -XX:MaxMetaspaceSize=N, Metaspace 최대 사이즈 설정. 이건 해줘야 한다.
    }
}
