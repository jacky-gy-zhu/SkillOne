# Docker

## official website
> https://www.docker.com/
> https://docs.docker.com/
> https://hub.docker.com/   (guangyuzhu / S***9)

## linux core version
> uname -r
> cat /etc/redhat-release

## install docker on linux centOS 6.5+
> yum install -y epel-release
> yum install -y docker-io
> after installation, go to check dock config files: [cat /etc/sysconfig/docker]
> start docker: [service docker start]
> check docker version: [docker version]

## show information
> show version [docker version]
> detail information of docker [docker info]
> show all commands of docker [docker help]
> show one particular command [docker `command` --help]

## Image commands
### list local images
> list all available images on local docker [docker images]
> list all (including intermediate images) images [docker images -a]
> only list image ID [docker images -q]
> only list image ID (including intermediate images ID) [docker images -qa]
> list images with descriptions [docker images --digests]
> show full image ID [docker images --no-trunc]
### search image
> search available image from docker hub [docker search `imageName`]
> search image from docker which rate more than a number [docker search -s 30 `imageName`]
> search image from docker with full description [docker search --no-trunc `imageName`]
> only show automated images [docker search --automated `imageName`]
### download image
> pull image from docker hub [docker pull `imageName`]
### remove image
> remove an image from local docker [docker rmi `imageName`]
> force to remove an image from local docker even when it's being used [docker rmi -f `imageName`]
> remove multiple images [docker rmi -f `imageName1` `imageName2`]
> remove all local images [docker rmi -f $(docker images -qa)]
### view image build history
> [docker history `imageID`]

## Container commands
### run container
> run local image [docker run `imageName`]
> run local image with interaction and console mod [docker run -it `imageName`]
> run local image and rename container [docker run -it --name `containerNewName` `imageName`]
> run local image with daemon mod [docker run -d `imageName`] container will exit right after run
> run local image with daemon mod and will not exit eg:[docker run -d centos /bin/sh -c "while true;do echo hello;sleep 2;done"]
> run local image at a certain port [docker run -it -p `8888`:`8080` `imageName`] eg:8888 is docker outside port while 8080 is container inside port
> run local image at random port [docker run -it -P `imageName`]
> run tomcat [docker run -d -p 7777:8080 tomcat] run in the background
> run local image with volumes [docker run -it -v /hostFullPath:/containerFullPath `imageName`]
> run vol with folder read-only [docker run -it -v /hostFullPath:/containerFullPath:ro `imageName`] host can write but container can only read
> run multiple vol with folder read-only [docker run -it -v /hostFullPath1:/containerFullPath1:ro -v /hostFullPath2:/containerFullPath2:ro `imageName`] host can write but container can only read
> If Docker cannot open directory : Permission denied [docker run -it -v /hostFullPath:/containerFullPath `imageName` --privileged=true]
### list containers
> list all running containers [docker ps]
> list last container [docker ps -l]
> list all containers including stopped [docker ps -a]
> list the latest 3 containers [docker ps -n `3`]
> only list container ID [docker ps -q]
### exit container
> stop and exit container when in container console [exit]
> exit but not stop container when in container console [ctrl+Q+P]
### start container
> start container which already created [docker start `containerID`]
> restart container [docker restart `containerID`]
> jump into an alive container [docker attach `containerID`]
### stop container
> stop container [docker stop `containerID`]
> force to stop container [docker kill `containerID`]
### remove container
> remove container which already exit [docker rm `containerID`]
> force to remove container even which is running [docker rm -f `containerID`]
> force to remove all containers [docker rm -f $(docker ps -qa)] / [docker ps -qa | xargs docker rm]
### view console log
> view container console log [docker log `containerID`]
> view container console log with timeline [docker log -t `containerID`]
> view container console log with timeline and keep alive [docker log -tf `containerID`]
> view container console log with timeline and see last 3 lines [docker log -t --tail `3` `containerID`]
### view container
> view pid inside of container [docker top `containerID`]
> view detail information of container [docker inspect `containerID`] it will return JSON string
> get result from an alive container [docker exec -t `containerID` `container command`] return result of command
> interact with container outside of container eg:[docker exec -it `centosID` `/bin/bash`]
### copy files inside of container
> copy files from container to host [docker cp `containerID`:/fullPath/file.txt /hostPath/file.txt]
### commit a new container
> [docker commit -a='author' -m='comments' `containerID` `skillone/tomcat:1.0`]

## Principle of Image
> Image is layer by layer, sharing same base images. Base images are read-only.

## DockFile
> extends [FROM `imageName`]
> multiple volumes [VOLUME ["/dataVolumeContainer1","/dataVolumeContainer2"]]
> build docker file to generate image [docker build -f `/dockerFilePath` -t `myNamespace/newImageName` .] . means building on the current folder
> vol2 extends vol1 [docker run -it --name `dc02` --volumes-from `dc01` `myNamespace/newImageName`]
> when the parent container be removed, the volumes in the child container won't be affected.
> every dockerFiles are from scratch [FROM scratch]
> every dockerFile command will commit a new image and commit
### DockerFile keywords
> [FROM] 基础镜像，当前新镜像是基于哪个镜像的
> [MAINTAINER] 镜像维护者的姓名和邮箱地址
> [RUN] 容器构建时需要运行的命令
> [EXPOSE] 当前容器对外暴露出的端口号
> [WORKDIR] 指定在创建容器后，终端默认登陆进来的工作目录，一个落脚点
> [ENV] 用来在构建镜像过程中设置环境变量
> [ADD] 将宿主机目录下的文件拷贝进镜像且ADD命令会自动处理URL和解压tar压缩包
> [COPY] 类似ADD，拷贝文件和目录到镜像中。将从构建上下文目录中<源路径>的文件/目录复制到新的一层的镜像内的<目标路径>位置 [COPY src dest] / [COPY ["src","dest"]]
> [VOLUME] 容器数据卷，用于数据保存和持久化工作
> [CMD] 指定一个容器启动时要运行的命令。DockerFile中可以有多个CMD指令，但只有最后一个生效，CMD会被docker run之后的参数替换
> [ENTRYPOINT] 指定一个容器启动时要运行的命令。ENTRYPOINT的目的和CMD一样，都是在指定容器启动程序及参数。（启动后的参数会append到之后）
>> ENTRYPOINT ["sleep"] 
>> CMD ["5"]
>> Command at Startup: sleep 5
> [ONBUILD] 当构建一个被继承的DockerFile时运行命令，父镜像在被子继承后父镜像的ONBUILD被触发


## Reference Link
> Intellij plugin [https://blog.csdn.net/boling_cavalry/article/details/100051325]

## Related account
> http://kodekloud.com/p/docker-labs {fullName: 'guangyu zhu', email: 'jacky.gy.zhu@gmail.com', password: 'Jacky123'}