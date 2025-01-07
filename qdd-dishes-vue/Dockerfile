# 使用官方 Nginx 镜像作为基础镜像
FROM nginx:latest

# 将 dist 目录复制到容器的 /dist 目录
COPY dist /dist

# 将 Nginx 配置文件复制到容器的根目录
COPY nginx.conf /nginx.conf

# 监听 Nginx 默认端口
EXPOSE 91

# 使用命令启动 Nginx，并指定配置文件路径
CMD ["nginx", "-g", "daemon off;", "-c", "/nginx.conf"]