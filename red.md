```json
{
 "requestHeader": {
  "xweb_xhr": "1",
  "Accept": "*/*",
  "Connection": "keep-alive",
  "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Mac MacWechat/WMPF MacWechat/3.8.7(0x13080712) UnifiedPCMacWechat(0xf26411f0) XWEB/16512",
  "Referer": "https://servicewechat.com/wx14eb1126b8a25636/162/page-frame.html",
  "Sec-Fetch-Site": "cross-site",
  "Sec-Fetch-Dest": "empty",
  "Host": "tcp-crm.com",
  "Accept-Encoding": "gzip, deflate, br",
  "Sec-Fetch-Mode": "cors",
  "Accept-Language": "zh-CN,zh;q=0.9",
  "Content-Length": "124",
  "Content-Type": "application/json"
 },
 "responseContent": "{\"code\":7012,\"message\":\"二维码已被使用\"}",
 "responseHeader": {
  "Cache-Control": "private",
  "Server": "Microsoft-IIS/10.0",
  "Access-Control-Allow-Origin": "https://smtzs.redbull-china.com",
  "X-AspNet-Version": "4.0.30319",
  "Content-Length": "47",
  "Date": "Thu, 09 Oct 2025 13:44:06 GMT",
  "Content-Type": "text/html; charset=utf-8",
  "X-Powered-By": "ASP.NET"
 },
 "url": "tcp-crm.com/dtc/CMiniApi/ScanPhase18/ScanVerify?SessionKey=S_RedBullDTC_7b3c2776-7f44-4af8-9eb8-5c35019a04c9"
}
```

```log

00:53:06.586 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x48794d43, L:/0:0:0:0:0:0:0:0:9999] READ: [id: 0x13c3202f, L:/127.0.0.1:9999 - R:/127.0.0.1:62312]
00:53:06.596 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x48794d43, L:/0:0:0:0:0:0:0:0:9999] READ COMPLETE
00:53:06.596 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x48794d43, L:/0:0:0:0:0:0:0:0:9999] READ: [id: 0x40b5f096, L:/127.0.0.1:9999 - R:/127.0.0.1:62313]
00:53:06.597 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x48794d43, L:/0:0:0:0:0:0:0:0:9999] READ COMPLETE
00:53:06.622 [nioEventLoopGroup-4-7] DEBUG io.netty.handler.ssl.SslHandler - [id: 0x40b5f096, L:/127.0.0.1:9999 - R:/127.0.0.1:62313] HANDSHAKEN: protocol:TLSv1.3 cipher suite:TLS_AES_128_GCM_SHA256
00:53:06.624 [nioEventLoopGroup-4-6] DEBUG io.netty.handler.ssl.SslHandler - [id: 0x13c3202f, L:/127.0.0.1:9999 - R:/127.0.0.1:62312] HANDSHAKEN: protocol:TLSv1.3 cipher suite:TLS_AES_128_GCM_SHA256
00:53:06.766 [nioEventLoopGroup-2-6] DEBUG io.netty.handler.ssl.util.InsecureTrustManagerFactory - Accepting a server certificate: CN=metaso.cn
00:53:06.820 [nioEventLoopGroup-2-6] DEBUG io.netty.handler.ssl.SslHandler - [id: 0x9d91901f, L:/192.168.3.68:62315 - R:metaso.cn/8.147.223.45:443] HANDSHAKEN: protocol:TLSv1.2 cipher suite:TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256
00:53:06.948 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - 
> > > > > > > > > > > > > > > > > > > > 
url: metaso.cn/api/ppt/user/info true

00:53:06.957 [nioEventLoopGroup-2-6] DEBUG io.netty.handler.codec.compression.ZlibCodecFactory - -Dio.netty.noJdkZlibDecoder: false
00:53:06.957 [nioEventLoopGroup-2-6] DEBUG io.netty.handler.codec.compression.ZlibCodecFactory - -Dio.netty.noJdkZlibEncoder: false
00:53:06.964 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - < < < < < < < < < < < < < < < < < < < < url:metaso.cn/api/ppt/user/info class io.netty.handler.codec.http.DefaultHttpRequest

00:53:06.964 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - METHOD: GET
00:53:06.964 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - FULL URI: /api/ppt/user/info
00:53:06.964 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - PATH: /api/ppt/user/info
00:53:06.964 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Host = metaso.cn
00:53:06.964 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Connection = keep-alive
00:53:06.964 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: metaso-pc = pc
00:53:06.964 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: sec-ch-ua-platform = "macOS"
00:53:06.964 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: User-Agent = Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/140.0.0.0 Safari/537.36
00:53:06.964 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept = application/json, text/plain, */*
00:53:06.964 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: sec-ch-ua = "Chromium";v="140", "Not=A?Brand";v="24", "Google Chrome";v="140"
00:53:06.964 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: token = wr8+pHu3KYryzz0O2MaBSNUZbVLjLUYC1FR4sKqSW0r7cpL+iG/2N5cdq9x0z7BqH9I/3fGDkqTQADHmkAvtcK9ZG7Vdy2Dd354NvB/+w8lpwIdooCagKVYnjYOZ3u3zgDXpuAN0C3iwSmnMD++iZA==
00:53:06.964 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: sec-ch-ua-mobile = ?0
00:53:06.964 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Site = same-origin
00:53:06.964 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Mode = cors
00:53:06.964 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Dest = empty
00:53:06.965 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept-Encoding = gzip, deflate, br, zstd
00:53:06.965 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept-Language = zh-CN,zh;q=0.9,en;q=0.8
00:53:06.965 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Cookie = JSESSIONID=811C966E5DBFF5332CE27C4B710FF80F; tid=59f42742-c9b0-4fec-8a84-ed389dd89f09; __eventn_id_UMO2dYNwFz=3f2d076n3i; uid=67c6c4e0f70db74066307f93; sid=e65e3a297c124c5caaf67997bfdbe8a0; aliyungf_tc=c667375641a3dbdeaf5f9bf1380ee47a367ef3ffdfc9aca2147afd72ed881bed; s=sbdpc1; s=sbdpc1; hideLeftMenu=1; newSearch=false
00:53:06.965 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - --- Headers End ---
00:53:07.996 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x48794d43, L:/0:0:0:0:0:0:0:0:9999] READ: [id: 0x6951a2fe, L:/127.0.0.1:9999 - R:/127.0.0.1:62321]
00:53:07.996 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x48794d43, L:/0:0:0:0:0:0:0:0:9999] READ COMPLETE
00:53:08.036 [nioEventLoopGroup-2-7] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - 
> > > > > > > > > > > > > > > > > > > > 
url: mmbiz.qpic.cn/mmbiz_png/IVHeS9UFDzEmxLg8rXV9iaYS8ibDnryqS8f9BB9RvLzxlg3l32Zw5pZsUC7t8fLdC4gwKAEP5cj2eVI1WeItTpyg/640?wx_fmt=png&wxfrom=200 true

00:53:08.036 [nioEventLoopGroup-2-7] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - < < < < < < < < < < < < < < < < < < < < url:mmbiz.qpic.cn/mmbiz_png/IVHeS9UFDzEmxLg8rXV9iaYS8ibDnryqS8f9BB9RvLzxlg3l32Zw5pZsUC7t8fLdC4gwKAEP5cj2eVI1WeItTpyg/640?wx_fmt=png&wxfrom=200 class io.netty.handler.codec.http.DefaultHttpRequest

00:53:08.036 [nioEventLoopGroup-2-7] INFO com.github.monkeywie.proxyee.util.HttpUtil - METHOD: GET
00:53:08.036 [nioEventLoopGroup-2-7] INFO com.github.monkeywie.proxyee.util.HttpUtil - FULL URI: /mmbiz_png/IVHeS9UFDzEmxLg8rXV9iaYS8ibDnryqS8f9BB9RvLzxlg3l32Zw5pZsUC7t8fLdC4gwKAEP5cj2eVI1WeItTpyg/640?wx_fmt=png&wxfrom=200
00:53:08.036 [nioEventLoopGroup-2-7] INFO com.github.monkeywie.proxyee.util.HttpUtil - PATH: /mmbiz_png/IVHeS9UFDzEmxLg8rXV9iaYS8ibDnryqS8f9BB9RvLzxlg3l32Zw5pZsUC7t8fLdC4gwKAEP5cj2eVI1WeItTpyg/640
00:53:08.036 [nioEventLoopGroup-2-7] INFO com.github.monkeywie.proxyee.util.HttpUtil - QUERY PARAM: wx_fmt = [png]
00:53:08.037 [nioEventLoopGroup-2-7] INFO com.github.monkeywie.proxyee.util.HttpUtil - QUERY PARAM: wxfrom = [200]
00:53:08.037 [nioEventLoopGroup-2-7] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Host = mmbiz.qpic.cn
00:53:08.037 [nioEventLoopGroup-2-7] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Proxy-Connection = keep-alive
00:53:08.037 [nioEventLoopGroup-2-7] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: User-Agent = Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Mac MacWechat/WMPF MacWechat/3.8.7(0x13080712) UnifiedPCMacWechat(0xf26411f0) XWEB/16512
00:53:08.037 [nioEventLoopGroup-2-7] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept = image/wxpic,image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8
00:53:08.037 [nioEventLoopGroup-2-7] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Referer = https://servicewechat.com/wx14eb1126b8a25636/162/page-frame.html
00:53:08.037 [nioEventLoopGroup-2-7] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept-Encoding = gzip, deflate
00:53:08.037 [nioEventLoopGroup-2-7] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept-Language = zh-CN,zh;q=0.9
00:53:08.037 [nioEventLoopGroup-2-7] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: If-Modified-Since = Thu, 07 Aug 2025 11:23:06 GMT
00:53:08.037 [nioEventLoopGroup-2-7] INFO com.github.monkeywie.proxyee.util.HttpUtil - --- Headers End ---
00:53:08.358 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x48794d43, L:/0:0:0:0:0:0:0:0:9999] READ: [id: 0x3b76077e, L:/127.0.0.1:9999 - R:/127.0.0.1:62330]
00:53:08.358 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x48794d43, L:/0:0:0:0:0:0:0:0:9999] READ COMPLETE
00:53:08.391 [nioEventLoopGroup-4-9] DEBUG io.netty.handler.ssl.SslHandler - [id: 0x3b76077e, L:/127.0.0.1:9999 - R:/127.0.0.1:62330] HANDSHAKEN: protocol:TLSv1.3 cipher suite:TLS_AES_128_GCM_SHA256
00:53:08.514 [nioEventLoopGroup-2-8] DEBUG io.netty.handler.ssl.util.InsecureTrustManagerFactory - Accepting a server certificate: CN=tcp-crm.com, O=天丝红牛（北京）贸易有限公司, ST=北京市, C=CN
00:53:08.549 [nioEventLoopGroup-2-8] DEBUG io.netty.handler.ssl.SslHandler - [id: 0xd30f6ddb, L:/192.168.3.68:62335 - R:tcp-crm.com/121.199.10.159:443] HANDSHAKEN: protocol:TLSv1.2 cipher suite:TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256
00:53:08.589 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - 
> > > > > > > > > > > > > > > > > > > > 
url: tcp-crm.com/dtc/CMiniApi/Configure/GetPublicConfig?SessionKey=S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0 true

00:53:08.590 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - < < < < < < < < < < < < < < < < < < < < url:tcp-crm.com/dtc/CMiniApi/Configure/GetPublicConfig?SessionKey=S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0 class io.netty.handler.codec.http.DefaultHttpRequest

00:53:08.590 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - METHOD: POST
00:53:08.590 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - FULL URI: /dtc/CMiniApi/Configure/GetPublicConfig?SessionKey=S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0
00:53:08.590 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - PATH: /dtc/CMiniApi/Configure/GetPublicConfig
00:53:08.590 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - QUERY PARAM: SessionKey = [S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0]
00:53:08.590 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Host = tcp-crm.com
00:53:08.590 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Connection = keep-alive
00:53:08.590 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Content-Length = 2
00:53:08.590 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: User-Agent = Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Mac MacWechat/WMPF MacWechat/3.8.7(0x13080712) UnifiedPCMacWechat(0xf26411f0) XWEB/16512
00:53:08.590 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: xweb_xhr = 1
00:53:08.590 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Content-Type = application/json
00:53:08.590 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept = */*
00:53:08.590 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Site = cross-site
00:53:08.590 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Mode = cors
00:53:08.590 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Dest = empty
00:53:08.590 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Referer = https://servicewechat.com/wx14eb1126b8a25636/162/page-frame.html
00:53:08.590 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept-Encoding = gzip, deflate, br
00:53:08.590 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept-Language = zh-CN,zh;q=0.9
00:53:08.590 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - --- Headers End ---
00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - 
> > > > > > > > > > > > > > > > > > > > 
url: tcp-crm.com/dtc/CMiniApi/OAuthProgram/GetUserInfo?SessionKey=S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0 true

00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - < < < < < < < < < < < < < < < < < < < < url:tcp-crm.com/dtc/CMiniApi/OAuthProgram/GetUserInfo?SessionKey=S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0 class io.netty.handler.codec.http.DefaultHttpRequest

00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - METHOD: POST
00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - FULL URI: /dtc/CMiniApi/OAuthProgram/GetUserInfo?SessionKey=S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0
00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - PATH: /dtc/CMiniApi/OAuthProgram/GetUserInfo
00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - QUERY PARAM: SessionKey = [S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0]
00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Host = tcp-crm.com
00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Connection = keep-alive
00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Content-Length = 2
00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: User-Agent = Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Mac MacWechat/WMPF MacWechat/3.8.7(0x13080712) UnifiedPCMacWechat(0xf26411f0) XWEB/16512
00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: xweb_xhr = 1
00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Content-Type = application/json
00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept = */*
00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Site = cross-site
00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Mode = cors
00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Dest = empty
00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Referer = https://servicewechat.com/wx14eb1126b8a25636/162/page-frame.html
00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept-Encoding = gzip, deflate, br
00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept-Language = zh-CN,zh;q=0.9
00:53:08.786 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - --- Headers End ---
00:53:08.854 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - 
> > > > > > > > > > > > > > > > > > > > 
url: tcp-crm.com/dtc/CMiniApi/OAuthProgram/GetUserInfo?SessionKey=S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0 true

00:53:08.854 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - < < < < < < < < < < < < < < < < < < < < url:tcp-crm.com/dtc/CMiniApi/OAuthProgram/GetUserInfo?SessionKey=S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0 class io.netty.handler.codec.http.DefaultHttpRequest

00:53:08.854 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - METHOD: POST
00:53:08.854 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - FULL URI: /dtc/CMiniApi/OAuthProgram/GetUserInfo?SessionKey=S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0
00:53:08.854 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - PATH: /dtc/CMiniApi/OAuthProgram/GetUserInfo
00:53:08.854 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - QUERY PARAM: SessionKey = [S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0]
00:53:08.854 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Host = tcp-crm.com
00:53:08.854 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Connection = keep-alive
00:53:08.854 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Content-Length = 2
00:53:08.854 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: User-Agent = Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Mac MacWechat/WMPF MacWechat/3.8.7(0x13080712) UnifiedPCMacWechat(0xf26411f0) XWEB/16512
00:53:08.854 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: xweb_xhr = 1
00:53:08.854 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Content-Type = application/json
00:53:08.854 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept = */*
00:53:08.854 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Site = cross-site
00:53:08.854 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Mode = cors
00:53:08.855 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Dest = empty
00:53:08.855 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Referer = https://servicewechat.com/wx14eb1126b8a25636/162/page-frame.html
00:53:08.855 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept-Encoding = gzip, deflate, br
00:53:08.855 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept-Language = zh-CN,zh;q=0.9
00:53:08.855 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - --- Headers End ---
00:53:08.900 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - 
> > > > > > > > > > > > > > > > > > > > 
url: tcp-crm.com/dtc/CMiniApi/ScanPhase18/GetActivityInfo?SessionKey=S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0 true

00:53:08.901 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - < < < < < < < < < < < < < < < < < < < < url:tcp-crm.com/dtc/CMiniApi/ScanPhase18/GetActivityInfo?SessionKey=S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0 class io.netty.handler.codec.http.DefaultHttpRequest

00:53:08.901 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - METHOD: POST
00:53:08.901 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - FULL URI: /dtc/CMiniApi/ScanPhase18/GetActivityInfo?SessionKey=S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0
00:53:08.901 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - PATH: /dtc/CMiniApi/ScanPhase18/GetActivityInfo
00:53:08.901 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - QUERY PARAM: SessionKey = [S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0]
00:53:08.901 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Host = tcp-crm.com
00:53:08.901 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Connection = keep-alive
00:53:08.901 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Content-Length = 2
00:53:08.901 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: User-Agent = Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Mac MacWechat/WMPF MacWechat/3.8.7(0x13080712) UnifiedPCMacWechat(0xf26411f0) XWEB/16512
00:53:08.901 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: xweb_xhr = 1
00:53:08.901 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Content-Type = application/json
00:53:08.901 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept = */*
00:53:08.901 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Site = cross-site
00:53:08.901 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Mode = cors
00:53:08.901 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Dest = empty
00:53:08.901 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Referer = https://servicewechat.com/wx14eb1126b8a25636/162/page-frame.html
00:53:08.901 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept-Encoding = gzip, deflate, br
00:53:08.901 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept-Language = zh-CN,zh;q=0.9
00:53:08.901 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - --- Headers End ---
00:53:08.944 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - 
> > > > > > > > > > > > > > > > > > > > 
url: tcp-crm.com/dtc/CMiniApi/OAuthProgram/GetUserInfo?SessionKey=S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0 true

00:53:08.944 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - < < < < < < < < < < < < < < < < < < < < url:tcp-crm.com/dtc/CMiniApi/OAuthProgram/GetUserInfo?SessionKey=S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0 class io.netty.handler.codec.http.DefaultHttpRequest

00:53:08.944 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - METHOD: POST
00:53:08.944 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - FULL URI: /dtc/CMiniApi/OAuthProgram/GetUserInfo?SessionKey=S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0
00:53:08.944 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - PATH: /dtc/CMiniApi/OAuthProgram/GetUserInfo
00:53:08.944 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - QUERY PARAM: SessionKey = [S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0]
00:53:08.944 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Host = tcp-crm.com
00:53:08.944 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Connection = keep-alive
00:53:08.944 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Content-Length = 2
00:53:08.944 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: User-Agent = Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Mac MacWechat/WMPF MacWechat/3.8.7(0x13080712) UnifiedPCMacWechat(0xf26411f0) XWEB/16512
00:53:08.944 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: xweb_xhr = 1
00:53:08.944 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Content-Type = application/json
00:53:08.944 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept = */*
00:53:08.944 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Site = cross-site
00:53:08.944 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Mode = cors
00:53:08.944 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Dest = empty
00:53:08.945 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Referer = https://servicewechat.com/wx14eb1126b8a25636/162/page-frame.html
00:53:08.945 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept-Encoding = gzip, deflate, br
00:53:08.945 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept-Language = zh-CN,zh;q=0.9
00:53:08.945 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - --- Headers End ---
00:53:08.992 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - 
> > > > > > > > > > > > > > > > > > > > 
url: tcp-crm.com/dtc/CMiniApi/ScanPhase18/ScanVerify?SessionKey=S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0 true

00:53:08.992 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - < < < < < < < < < < < < < < < < < < < < url:tcp-crm.com/dtc/CMiniApi/ScanPhase18/ScanVerify?SessionKey=S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0 class io.netty.handler.codec.http.DefaultHttpRequest

00:53:08.992 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - METHOD: POST
00:53:08.992 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - FULL URI: /dtc/CMiniApi/ScanPhase18/ScanVerify?SessionKey=S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0
00:53:08.992 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - PATH: /dtc/CMiniApi/ScanPhase18/ScanVerify
00:53:08.992 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - QUERY PARAM: SessionKey = [S_RedBullDTC_7ae4cbcc-acfb-4805-a7a3-27611d7bbce0]
00:53:08.992 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Host = tcp-crm.com
00:53:08.992 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Connection = keep-alive
00:53:08.992 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Content-Length = 124
00:53:08.992 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: User-Agent = Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Mac MacWechat/WMPF MacWechat/3.8.7(0x13080712) UnifiedPCMacWechat(0xf26411f0) XWEB/16512
00:53:08.992 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: xweb_xhr = 1
00:53:08.992 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Content-Type = application/json
00:53:08.992 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept = */*
00:53:08.992 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Site = cross-site
00:53:08.992 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Mode = cors
00:53:08.992 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Dest = empty
00:53:08.992 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Referer = https://servicewechat.com/wx14eb1126b8a25636/162/page-frame.html
00:53:08.993 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept-Encoding = gzip, deflate, br
00:53:08.993 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept-Language = zh-CN,zh;q=0.9
00:53:08.993 [nioEventLoopGroup-2-8] INFO com.github.monkeywie.proxyee.util.HttpUtil - --- Headers End ---
00:53:09.018 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x48794d43, L:/0:0:0:0:0:0:0:0:9999] READ: [id: 0x349d663c, L:/127.0.0.1:9999 - R:/127.0.0.1:62340]
00:53:09.018 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x48794d43, L:/0:0:0:0:0:0:0:0:9999] READ COMPLETE
00:53:09.036 [nioEventLoopGroup-4-10] DEBUG io.netty.handler.ssl.SslHandler - [id: 0x349d663c, L:/127.0.0.1:9999 - R:/127.0.0.1:62340] HANDSHAKEN: protocol:TLSv1.3 cipher suite:TLS_AES_128_GCM_SHA256
00:53:10.065 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - 
> > > > > > > > > > > > > > > > > > > > 
url: metaso.cn/api/ppt/user/info true

00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - < < < < < < < < < < < < < < < < < < < < url:metaso.cn/api/ppt/user/info class io.netty.handler.codec.http.DefaultHttpRequest

00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - METHOD: GET
00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - FULL URI: /api/ppt/user/info
00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - PATH: /api/ppt/user/info
00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Host = metaso.cn
00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Connection = keep-alive
00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: metaso-pc = pc
00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: sec-ch-ua-platform = "macOS"
00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: User-Agent = Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/140.0.0.0 Safari/537.36
00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept = application/json, text/plain, */*
00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: sec-ch-ua = "Chromium";v="140", "Not=A?Brand";v="24", "Google Chrome";v="140"
00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: token = wr8+pHu3KYryzz0O2MaBSNUZbVLjLUYC1FR4sKqSW0r7cpL+iG/2N5cdq9x0z7BqH9I/3fGDkqTQADHmkAvtcK9ZG7Vdy2Dd354NvB/+w8lpwIdooCagKVYnjYOZ3u3zgDXpuAN0C3iwSmnMD++iZA==
00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: sec-ch-ua-mobile = ?0
00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Site = same-origin
00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Mode = cors
00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Sec-Fetch-Dest = empty
00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept-Encoding = gzip, deflate, br, zstd
00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Accept-Language = zh-CN,zh;q=0.9,en;q=0.8
00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - HEADER: Cookie = JSESSIONID=2B007DF7AC20226C10B31BCBB1E8FB68; tid=59f42742-c9b0-4fec-8a84-ed389dd89f09; __eventn_id_UMO2dYNwFz=3f2d076n3i; uid=67c6c4e0f70db74066307f93; sid=e65e3a297c124c5caaf67997bfdbe8a0; aliyungf_tc=c667375641a3dbdeaf5f9bf1380ee47a367ef3ffdfc9aca2147afd72ed881bed; s=sbdpc1; s=sbdpc1; hideLeftMenu=1; newSearch=false
00:53:10.066 [nioEventLoopGroup-2-6] INFO com.github.monkeywie.proxyee.util.HttpUtil - --- Headers End ---
00:53:11.017 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x48794d43, L:/0:0:0:0:0:0:0:0:9999] READ: [id: 0xb2446ea3, L:/127.0.0.1:9999 - R:/127.0.0.1:62344]
00:53:11.018 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x48794d43, L:/0:0:0:0:0:0:0:0:9999] READ COMPLETE
00:53:11.035 [nioEventLoopGroup-4-11] DEBUG io.netty.handler.ssl.SslHandler - [id: 0xb2446ea3, L:/127.0.0.1:9999 - R:/127.0.0.1:62344] HANDSHAKEN: protocol:TLSv1.3 cipher suite:TLS_AES_128_GCM_SHA256
00:53:11.595 [nioEventLoopGroup-2-9] DEBUG io.netty.handler.ssl.util.InsecureTrustManagerFactory - Accepting a server certificate: CN=a.clarity.ms, O=Microsoft Corporation, L=Redmond, ST=WA, C=US


```


```log

21:44:11.405 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x81ad797c, L:/0:0:0:0:0:0:0:0:9999] READ: [id: 0xabda188c, L:/127.0.0.1:9999 - R:/127.0.0.1:60330]
21:44:11.406 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x81ad797c, L:/0:0:0:0:0:0:0:0:9999] READ COMPLETE
21:44:11.418 [nioEventLoopGroup-4-1] DEBUG io.netty.buffer.AbstractByteBuf - -Dio.netty.buffer.checkAccessible: true
21:44:11.418 [nioEventLoopGroup-4-1] DEBUG io.netty.buffer.AbstractByteBuf - -Dio.netty.buffer.checkBounds: true
21:44:11.419 [nioEventLoopGroup-4-1] DEBUG io.netty.util.ResourceLeakDetectorFactory - Loaded default ResourceLeakDetector: io.netty.util.ResourceLeakDetector@29798558
21:44:11.429 [nioEventLoopGroup-4-1] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.maxCapacityPerThread: 4096
21:44:11.429 [nioEventLoopGroup-4-1] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.ratio: 8
21:44:11.429 [nioEventLoopGroup-4-1] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.chunkSize: 32
21:44:11.429 [nioEventLoopGroup-4-1] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.blocking: false
21:44:11.429 [nioEventLoopGroup-4-1] DEBUG io.netty.util.Recycler - -Dio.netty.recycler.batchFastThreadLocalOnly: true
21:44:11.483 [nioEventLoopGroup-2-1] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept -
> > > > > > > > > > > > > > > > > > > >
url: mmbiz.qpic.cn/mmbiz_png/IVHeS9UFDzEmxLg8rXV9iaYS8ibDnryqS8f9BB9RvLzxlg3l32Zw5pZsUC7t8fLdC4gwKAEP5cj2eVI1WeItTpyg/640?wx_fmt=png&wxfrom=200 true

21:44:11.487 [nioEventLoopGroup-2-1] DEBUG io.netty.handler.codec.compression.Brotli - brotli4j not in the classpath; Brotli support will be unavailable.
21:44:11.489 [nioEventLoopGroup-2-1] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - < < < < < < < < < < < < < < < < < < < <
 url: mmbiz.qpic.cn/mmbiz_png/IVHeS9UFDzEmxLg8rXV9iaYS8ibDnryqS8f9BB9RvLzxlg3l32Zw5pZsUC7t8fLdC4gwKAEP5cj2eVI1WeItTpyg/640?wx_fmt=png&wxfrom=200
 headers: image/webp

21:44:11.665 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x81ad797c, L:/0:0:0:0:0:0:0:0:9999] READ: [id: 0x174dfd4d, L:/127.0.0.1:9999 - R:/127.0.0.1:60339]
21:44:11.665 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x81ad797c, L:/0:0:0:0:0:0:0:0:9999] READ COMPLETE
21:44:11.795 [nioEventLoopGroup-4-2] DEBUG com.github.monkeywie.proxyee.crt.CertUtilsLoader - Generator implementation loaded (Name: BouncyCastle, implementation class: com.github.monkeywie.proxyee.crt.service.bc.BouncyCastleCertGenerator)
21:44:11.978 [nioEventLoopGroup-4-2] DEBUG io.netty.handler.ssl.SslHandler - [id: 0x174dfd4d, L:/127.0.0.1:9999 - R:/127.0.0.1:60339] HANDSHAKEN: protocol:TLSv1.3 cipher suite:TLS_AES_128_GCM_SHA256
21:44:12.078 [nioEventLoopGroup-2-2] DEBUG io.netty.handler.ssl.util.InsecureTrustManagerFactory - Accepting a server certificate: CN=tcp-crm.com, O=天丝红牛（北京）贸易有限公司, ST=北京市, C=CN
21:44:12.083 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x81ad797c, L:/0:0:0:0:0:0:0:0:9999] READ: [id: 0xff937dc0, L:/127.0.0.1:9999 - R:/127.0.0.1:60349]
21:44:12.084 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x81ad797c, L:/0:0:0:0:0:0:0:0:9999] READ COMPLETE
21:44:12.100 [nioEventLoopGroup-4-3] DEBUG io.netty.handler.ssl.SslHandler - [id: 0xff937dc0, L:/127.0.0.1:9999 - R:/127.0.0.1:60349] HANDSHAKEN: protocol:TLSv1.3 cipher suite:TLS_AES_128_GCM_SHA256
21:44:12.125 [nioEventLoopGroup-2-2] DEBUG io.netty.handler.ssl.SslHandler - [id: 0xd5dd4841, L:/192.168.3.68:60346 - R:tcp-crm.com/121.199.10.159:443] HANDSHAKEN: protocol:TLSv1.2 cipher suite:TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256
21:44:12.165 [nioEventLoopGroup-2-2] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept -
> > > > > > > > > > > > > > > > > > > >
url: tcp-crm.com/dtc/CMiniApi/Configure/GetPublicConfig?SessionKey=S_RedBullDTC_7b3c2776-7f44-4af8-9eb8-5c35019a04c9 true

21:44:12.166 [nioEventLoopGroup-2-2] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - < < < < < < < < < < < < < < < < < < < <
 url: tcp-crm.com/dtc/CMiniApi/Configure/GetPublicConfig?SessionKey=S_RedBullDTC_7b3c2776-7f44-4af8-9eb8-5c35019a04c9
 headers: text/html; charset=utf-8

21:44:12.242 [nioEventLoopGroup-2-2] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - httpCache: {"requestHeader":{"xweb_xhr":"1","Accept":"*/*","Connection":"keep-alive","User-Agent":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Mac MacWechat/WMPF MacWechat/3.8.7(0x13080712) UnifiedPCMacWechat(0xf26411f0) XWEB/16512","Referer":"https://servicewechat.com/wx14eb1126b8a25636/162/page-frame.html","Sec-Fetch-Site":"cross-site","Sec-Fetch-Dest":"empty","Host":"tcp-crm.com","Accept-Encoding":"gzip, deflate, br","Sec-Fetch-Mode":"cors","Accept-Language":"zh-CN,zh;q=0.9","Content-Length":"2","Content-Type":"application/json"},"responseContent":"{\"code\":0,\"message\":\"ok\",\"data\":{\"Modular\":{\"Mall\":false,\"IntegralLuckdraw\":false}}}","responseHeader":{"Cache-Control":"private","Server":"Microsoft-IIS/10.0","Access-Control-Allow-Origin":"https://smtzs.redbull-china.com","X-AspNet-Version":"4.0.30319","Content-Length":"84","Date":"Thu, 09 Oct 2025 13:44:06 GMT","Content-Type":"text/html; charset=utf-8","X-Powered-By":"ASP.NET"},"url":"tcp-crm.com/dtc/CMiniApi/Configure/GetPublicConfig?SessionKey=S_RedBullDTC_7b3c2776-7f44-4af8-9eb8-5c35019a04c9"}
21:44:12.290 [nioEventLoopGroup-2-2] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept -
> > > > > > > > > > > > > > > > > > > >
url: tcp-crm.com/dtc/CMiniApi/OAuthProgram/GetUserInfo?SessionKey=S_RedBullDTC_7b3c2776-7f44-4af8-9eb8-5c35019a04c9 true

21:44:12.290 [nioEventLoopGroup-2-2] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - < < < < < < < < < < < < < < < < < < < <
 url: tcp-crm.com/dtc/CMiniApi/OAuthProgram/GetUserInfo?SessionKey=S_RedBullDTC_7b3c2776-7f44-4af8-9eb8-5c35019a04c9
 headers: text/html; charset=utf-8

21:44:12.291 [nioEventLoopGroup-2-2] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - httpCache: {"requestHeader":{"xweb_xhr":"1","Accept":"*/*","Connection":"keep-alive","User-Agent":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Mac MacWechat/WMPF MacWechat/3.8.7(0x13080712) UnifiedPCMacWechat(0xf26411f0) XWEB/16512","Referer":"https://servicewechat.com/wx14eb1126b8a25636/162/page-frame.html","Sec-Fetch-Site":"cross-site","Sec-Fetch-Dest":"empty","Host":"tcp-crm.com","Accept-Encoding":"gzip, deflate, br","Sec-Fetch-Mode":"cors","Accept-Language":"zh-CN,zh;q=0.9","Content-Length":"2","Content-Type":"application/json"},"responseContent":"{\"code\":0,\"message\":\"ok\",\"data\":{\"ID\":\"1461934261773795336\",\"OpenID\":\"o4tBT5aWUtuLevi4jOA97pCUHhBE\",\"UnionID\":\"o19en6PlXjDpqP0INh3J73j7xYHw\",\"NickName\":\"红小牛\",\"HeadImage\":\"https://static.tcp-crm.com/2021utc/images/touxiang.png\",\"Mobile\":\"18514475123\",\"IP\":\"116.21.131.122\",\"Prov\":\"广东\",\"City\":\"广州\",\"IsOAuthUserInfo\":true,\"IsOAuthMobile\":true,\"IsOAuthLBS\":false,\"LBSLong\":0.0000000,\"LBSLat\":0.0000000,\"AccountFlag\":0,\"AccountLV\":0,\"OnLine\":1760014665260.0,\"OAuthUserInfoTime\":1750768788907.0,\"LV\":1,\"GrowUpValue\":24,\"Point\":74,\"TotalPoint\":74,\"MemberCardCode\":\"57503628\",\"InvitationCount\":0,\"IsSaveUserInfo\":false,\"IsSubscribe\":false,\"IsSubscribeIntegralTask\":false,\"IsSubscribeGrowUpTask\":false,\"IsHead\":false,\"IsHightRisk\":false,\"Score\":0.00,\"AddTime\":1750768782250.0,\"IsDel\":false}}","responseHeader":{"Cache-Control":"private","Server":"Microsoft-IIS/10.0","Access-Control-Allow-Origin":"https://smtzs.redbull-china.com","X-AspNet-Version":"4.0.30319","Content-Length":"796","Date":"Thu, 09 Oct 2025 13:44:06 GMT","Content-Type":"text/html; charset=utf-8","X-Powered-By":"ASP.NET"},"url":"tcp-crm.com/dtc/CMiniApi/OAuthProgram/GetUserInfo?SessionKey=S_RedBullDTC_7b3c2776-7f44-4af8-9eb8-5c35019a04c9"}
21:44:12.316 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x81ad797c, L:/0:0:0:0:0:0:0:0:9999] READ: [id: 0x31aad98b, L:/127.0.0.1:9999 - R:/127.0.0.1:60351]
21:44:12.316 [nioEventLoopGroup-3-1] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0x81ad797c, L:/0:0:0:0:0:0:0:0:9999] READ COMPLETE
21:44:12.329 [nioEventLoopGroup-4-4] DEBUG io.netty.handler.ssl.SslHandler - [id: 0x31aad98b, L:/127.0.0.1:9999 - R:/127.0.0.1:60351] HANDSHAKEN: protocol:TLSv1.3 cipher suite:TLS_AES_128_GCM_SHA256
21:44:12.340 [nioEventLoopGroup-2-2] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept -
> > > > > > > > > > > > > > > > > > > >
url: tcp-crm.com/dtc/CMiniApi/ScanPhase18/GetActivityInfo?SessionKey=S_RedBullDTC_7b3c2776-7f44-4af8-9eb8-5c35019a04c9 true

21:44:12.340 [nioEventLoopGroup-2-2] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - < < < < < < < < < < < < < < < < < < < <
 url: tcp-crm.com/dtc/CMiniApi/ScanPhase18/GetActivityInfo?SessionKey=S_RedBullDTC_7b3c2776-7f44-4af8-9eb8-5c35019a04c9
 headers: text/html; charset=utf-8

21:44:12.340 [nioEventLoopGroup-2-2] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - httpCache: {"requestHeader":{"xweb_xhr":"1","Accept":"*/*","Connection":"keep-alive","User-Agent":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Mac MacWechat/WMPF MacWechat/3.8.7(0x13080712) UnifiedPCMacWechat(0xf26411f0) XWEB/16512","Referer":"https://servicewechat.com/wx14eb1126b8a25636/162/page-frame.html","Sec-Fetch-Site":"cross-site","Sec-Fetch-Dest":"empty","Host":"tcp-crm.com","Accept-Encoding":"gzip, deflate, br","Sec-Fetch-Mode":"cors","Accept-Language":"zh-CN,zh;q=0.9","Content-Length":"2","Content-Type":"application/json"},"responseContent":"{\"code\":0,\"message\":\"ok\",\"data\":{\"ScanCount\":12,\"Chance\":0}}","responseHeader":{"Cache-Control":"private","Server":"Microsoft-IIS/10.0","Access-Control-Allow-Origin":"https://smtzs.redbull-china.com","X-AspNet-Version":"4.0.30319","Content-Length":"60","Date":"Thu, 09 Oct 2025 13:44:06 GMT","Content-Type":"text/html; charset=utf-8","X-Powered-By":"ASP.NET"},"url":"tcp-crm.com/dtc/CMiniApi/ScanPhase18/GetActivityInfo?SessionKey=S_RedBullDTC_7b3c2776-7f44-4af8-9eb8-5c35019a04c9"}
21:44:12.398 [nioEventLoopGroup-2-2] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept -
> > > > > > > > > > > > > > > > > > > >
url: tcp-crm.com/dtc/CMiniApi/OAuthProgram/GetUserInfo?SessionKey=S_RedBullDTC_7b3c2776-7f44-4af8-9eb8-5c35019a04c9 true

21:44:12.399 [nioEventLoopGroup-2-2] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - < < < < < < < < < < < < < < < < < < < <
 url: tcp-crm.com/dtc/CMiniApi/OAuthProgram/GetUserInfo?SessionKey=S_RedBullDTC_7b3c2776-7f44-4af8-9eb8-5c35019a04c9
 headers: text/html; charset=utf-8

21:44:12.399 [nioEventLoopGroup-2-2] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - httpCache: {"requestHeader":{"xweb_xhr":"1","Accept":"*/*","Connection":"keep-alive","User-Agent":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Mac MacWechat/WMPF MacWechat/3.8.7(0x13080712) UnifiedPCMacWechat(0xf26411f0) XWEB/16512","Referer":"https://servicewechat.com/wx14eb1126b8a25636/162/page-frame.html","Sec-Fetch-Site":"cross-site","Sec-Fetch-Dest":"empty","Host":"tcp-crm.com","Accept-Encoding":"gzip, deflate, br","Sec-Fetch-Mode":"cors","Accept-Language":"zh-CN,zh;q=0.9","Content-Length":"2","Content-Type":"application/json"},"responseContent":"{\"code\":0,\"message\":\"ok\",\"data\":{\"ID\":\"1461934261773795336\",\"OpenID\":\"o4tBT5aWUtuLevi4jOA97pCUHhBE\",\"UnionID\":\"o19en6PlXjDpqP0INh3J73j7xYHw\",\"NickName\":\"红小牛\",\"HeadImage\":\"https://static.tcp-crm.com/2021utc/images/touxiang.png\",\"Mobile\":\"18514475123\",\"IP\":\"116.21.131.122\",\"Prov\":\"广东\",\"City\":\"广州\",\"IsOAuthUserInfo\":true,\"IsOAuthMobile\":true,\"IsOAuthLBS\":false,\"LBSLong\":0.0000000,\"LBSLat\":0.0000000,\"AccountFlag\":0,\"AccountLV\":0,\"OnLine\":1760014665260.0,\"OAuthUserInfoTime\":1750768788907.0,\"LV\":1,\"GrowUpValue\":24,\"Point\":74,\"TotalPoint\":74,\"MemberCardCode\":\"57503628\",\"InvitationCount\":0,\"IsSaveUserInfo\":false,\"IsSubscribe\":false,\"IsSubscribeIntegralTask\":false,\"IsSubscribeGrowUpTask\":false,\"IsHead\":false,\"IsHightRisk\":false,\"Score\":0.00,\"AddTime\":1750768782250.0,\"IsDel\":false}}","responseHeader":{"Cache-Control":"private","Server":"Microsoft-IIS/10.0","Access-Control-Allow-Origin":"https://smtzs.redbull-china.com","X-AspNet-Version":"4.0.30319","Content-Length":"796","Date":"Thu, 09 Oct 2025 13:44:06 GMT","Content-Type":"text/html; charset=utf-8","X-Powered-By":"ASP.NET"},"url":"tcp-crm.com/dtc/CMiniApi/OAuthProgram/GetUserInfo?SessionKey=S_RedBullDTC_7b3c2776-7f44-4af8-9eb8-5c35019a04c9"}
21:44:12.442 [nioEventLoopGroup-2-2] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept -
> > > > > > > > > > > > > > > > > > > >
url: tcp-crm.com/dtc/CMiniApi/OAuthProgram/GetUserInfo?SessionKey=S_RedBullDTC_7b3c2776-7f44-4af8-9eb8-5c35019a04c9 true

21:44:12.443 [nioEventLoopGroup-2-2] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - < < < < < < < < < < < < < < < < < < < <
 url: tcp-crm.com/dtc/CMiniApi/OAuthProgram/GetUserInfo?SessionKey=S_RedBullDTC_7b3c2776-7f44-4af8-9eb8-5c35019a04c9
 headers: text/html; charset=utf-8

21:44:12.443 [nioEventLoopGroup-2-2] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - httpCache: {"requestHeader":{"xweb_xhr":"1","Accept":"*/*","Connection":"keep-alive","User-Agent":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Mac MacWechat/WMPF MacWechat/3.8.7(0x13080712) UnifiedPCMacWechat(0xf26411f0) XWEB/16512","Referer":"https://servicewechat.com/wx14eb1126b8a25636/162/page-frame.html","Sec-Fetch-Site":"cross-site","Sec-Fetch-Dest":"empty","Host":"tcp-crm.com","Accept-Encoding":"gzip, deflate, br","Sec-Fetch-Mode":"cors","Accept-Language":"zh-CN,zh;q=0.9","Content-Length":"2","Content-Type":"application/json"},"responseContent":"{\"code\":0,\"message\":\"ok\",\"data\":{\"ID\":\"1461934261773795336\",\"OpenID\":\"o4tBT5aWUtuLevi4jOA97pCUHhBE\",\"UnionID\":\"o19en6PlXjDpqP0INh3J73j7xYHw\",\"NickName\":\"红小牛\",\"HeadImage\":\"https://static.tcp-crm.com/2021utc/images/touxiang.png\",\"Mobile\":\"18514475123\",\"IP\":\"116.21.131.122\",\"Prov\":\"广东\",\"City\":\"广州\",\"IsOAuthUserInfo\":true,\"IsOAuthMobile\":true,\"IsOAuthLBS\":false,\"LBSLong\":0.0000000,\"LBSLat\":0.0000000,\"AccountFlag\":0,\"AccountLV\":0,\"OnLine\":1760014665260.0,\"OAuthUserInfoTime\":1750768788907.0,\"LV\":1,\"GrowUpValue\":24,\"Point\":74,\"TotalPoint\":74,\"MemberCardCode\":\"57503628\",\"InvitationCount\":0,\"IsSaveUserInfo\":false,\"IsSubscribe\":false,\"IsSubscribeIntegralTask\":false,\"IsSubscribeGrowUpTask\":false,\"IsHead\":false,\"IsHightRisk\":false,\"Score\":0.00,\"AddTime\":1750768782250.0,\"IsDel\":false}}","responseHeader":{"Cache-Control":"private","Server":"Microsoft-IIS/10.0","Access-Control-Allow-Origin":"https://smtzs.redbull-china.com","X-AspNet-Version":"4.0.30319","Content-Length":"796","Date":"Thu, 09 Oct 2025 13:44:06 GMT","Content-Type":"text/html; charset=utf-8","X-Powered-By":"ASP.NET"},"url":"tcp-crm.com/dtc/CMiniApi/OAuthProgram/GetUserInfo?SessionKey=S_RedBullDTC_7b3c2776-7f44-4af8-9eb8-5c35019a04c9"}
21:44:12.490 [nioEventLoopGroup-2-2] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept -
> > > > > > > > > > > > > > > > > > > >
url: tcp-crm.com/dtc/CMiniApi/ScanPhase18/ScanVerify?SessionKey=S_RedBullDTC_7b3c2776-7f44-4af8-9eb8-5c35019a04c9 true
url: tcp-crm.com/dtc/CMiniApi/ScanPhase18/ScanVerify?SessionKey=S_RedBullDTC_f062f683-f47f-4f39-b2e7-29ddc407cce9 true

21:44:12.490 [nioEventLoopGroup-2-2] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - < < < < < < < < < < < < < < < < < < < <
 url: tcp-crm.com/dtc/CMiniApi/ScanPhase18/ScanVerify?SessionKey=S_RedBullDTC_7b3c2776-7f44-4af8-9eb8-5c35019a04c9
 headers: text/html; charset=utf-8

21:44:12.491 [nioEventLoopGroup-2-2] INFO com.github.monkeywie.proxyee.website.HttpCacheIntercept - httpCache: {"requestHeader":{"xweb_xhr":"1","Accept":"*/*","Connection":"keep-alive","User-Agent":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Mac MacWechat/WMPF MacWechat/3.8.7(0x13080712) UnifiedPCMacWechat(0xf26411f0) XWEB/16512","Referer":"https://servicewechat.com/wx14eb1126b8a25636/162/page-frame.html","Sec-Fetch-Site":"cross-site","Sec-Fetch-Dest":"empty","Host":"tcp-crm.com","Accept-Encoding":"gzip, deflate, br","Sec-Fetch-Mode":"cors","Accept-Language":"zh-CN,zh;q=0.9","Content-Length":"124","Content-Type":"application/json"},"responseContent":"{\"code\":7012,\"message\":\"二维码已被使用\"}","responseHeader":{"Cache-Control":"private","Server":"Microsoft-IIS/10.0","Access-Control-Allow-Origin":"https://smtzs.redbull-china.com","X-AspNet-Version":"4.0.30319","Content-Length":"47","Date":"Thu, 09 Oct 2025 13:44:06 GMT","Content-Type":"text/html; charset=utf-8","X-Powered-By":"ASP.NET"},"url":"tcp-crm.com/dtc/CMiniApi/ScanPhase18/ScanVerify?SessionKey=S_RedBullDTC_7b3c2776-7f44-4af8-9eb8-5c35019a04c9"}

```


