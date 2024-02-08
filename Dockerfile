FROM jenkins/jenkins:2.426.3-jdk17
LABEL author="lgsduran@gmail.com"
USER root

RUN apt-get update -q \
  && apt-get install -qy --no-install-recommends \
  maven \
  libgtk2.0-0 \ 
  libgtk-3-0 \
  libgbm-dev \
  libnotify-dev \
  libgconf-2-4 \
  libnss3 \
  libxss1 \
  libasound2 \
  libxtst6 \
  xauth \
  xvfb \
  wget \
  curl \
  sudo

RUN wget -q https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
RUN apt-get install -y ./google-chrome-stable_current_amd64.deb

COPY --chown=jenkins:jenkins plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli -f /usr/share/jenkins/ref/plugins.txt

RUN apt-get clean \
  && rm -rf /var/lib/apt

USER jenkins