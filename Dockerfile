FROM jdk:8u202
ADD target/demo.jar /root/app.jar
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8
ENV JAVA_HOME /opt/java
ENV PATH $JAVA_HOME/bin:$PATH
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java", "-jar", "-server", "-Dserver.port=9000", "-Xmx256m", "-Xms256m","-Xmn96m", "-XX:MaxDirectMemorySize=256m", "-XX:SurvivorRatio=8", "-Dreactor.netty.pool.leasingStrategy=lifo", "-XX:+HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=/logs/dump", "-Xdebug","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=6000", "/root/app.jar"]