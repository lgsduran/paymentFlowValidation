FROM jenkins/jenkins:2.426.3-jdk17
LABEL author="lgsduran@gmail.com"
USER root

RUN apt-get update -q \
  && apt-get install -qy --no-install-recommends \
  maven \
  sudo  \
  git

USER jenkins