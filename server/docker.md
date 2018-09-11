sh -x deploy-docker.sh pahf st
docker-compose  -f docker-compose.yml up -d



1.
iptables中没有docker chain 会拦截docker 访问外网
docker run -dit -p 12003:12003 --net=host zulin-erp:v4 /bin/bash
