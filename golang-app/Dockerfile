FROM golang
WORKDIR /dist/

ENV GOPROXY=https://goproxy.cn

ADD main.go /dist/main.go
ADD go.mod /dist/
RUN go build


FROM debian:11
WORKDIR /dist/
COPY --from=0 /dist/golang-app /dist/

EXPOSE 8000

ENTRYPOINT ["/dist/golang-app"]
