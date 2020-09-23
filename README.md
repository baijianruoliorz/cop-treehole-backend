# cop-treehole-backend



这两天写的代码由于网络问题没提交上去...

所以做成了镜像,可以直接使用,但是应该是无法更新这个项目的代码了,请不要clone,代码不是完整的.

有需要完整源码的~~还可能会根据需求增加~~可以联系QQ:1099462011

docker deploy:
```
docker rm treehole -f

docker rmi liqiqiorz/treehole

docker run --name treehole -d -p 8006:8006 liqiqiorz/treehole:latest
```

backendApi:[treehole-sonic-backendApi](http://112.126.78.122:8006/swagger-ui.html#/)
