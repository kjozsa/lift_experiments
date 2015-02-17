FROM base/archlinux
MAINTAINER Kristof Jozsa <kristof.jozsa@gmail.com>

RUN pacman -Sy --noconfirm vim jdk8-openjdk
RUN useradd -ms /bin/bash lifter

EXPOSE 8080

USER lifter
ENV HOME /home/lifter
WORKDIR /home/lifter

ADD target/scala-2.11/lift-experiments_2.11-0.0.1-one-jar.jar /home/lifter/app.jar

CMD ["/usr/bin/java", "-jar", "/home/lifter/app.jar"]
