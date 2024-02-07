FROM ubuntu:22.04

USER root

RUN apt-get update -q \
  && apt-get install -qy --no-install-recommends \
  openjdk-8-jre \
  maven \
  wget \
  curl \
  sudo  \
  git \
  gnupg \
  systemctl \
  lynx \
  iputils-ping \
  vim

RUN wget -O /usr/share/keyrings/jenkins-keyring.asc https://pkg.jenkins.io/debian/jenkins.io-2023.key

RUN echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] https://pkg.jenkins.io/debian binary/ | tee /etc/apt/sources.list.d/jenkins.list > /dev/null

RUN apt-get update -q \
  && apt-get install -qy jenkins \
  && apt-get clean \
  && rm -rf /var/lib/apt

EXPOSE 50000

RUN systemctl enable jenkins
#RUN systemctl status jenkins

USER jenkins