FROM jenkins:2.32.3

USER root
RUN mkdir /var/log/jenkins
RUN chown -R  jenkins:jenkins /var/log/jenkins
RUN mkdir /cdb
RUN chown -R  jenkins:jenkins /cdb
# RUN mkdir /cdb_war
# RUN chown -R  jenkins:jenkins /cdb_war
RUN apt-get update \
      && apt-get install -y sudo \
      && rm -rf /var/lib/apt/lists/*
RUN echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers
RUN groupadd -g 999 docker && usermod -a -G docker jenkins

USER jenkins
