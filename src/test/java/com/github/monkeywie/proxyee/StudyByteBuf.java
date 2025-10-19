package com.github.monkeywie.proxyee;

import com.github.monkeywie.proxyee.util.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import org.junit.Test;

import java.nio.charset.Charset;


public class StudyByteBuf {

    private final static InternalLogger log = InternalLoggerFactory.getInstance(StudyByteBuf.class);

    // String -> ByteBuf
    ByteBuf baseByteBuf = Unpooled.copiedBuffer("four you", CharsetUtil.UTF_8);

    @Test
    public void copyByteBuf() {
        ByteBuf bufCopy = baseByteBuf.copy(0, 3);
        System.out.println("baseByteBuf:" + bufCopy.toString(CharsetUtil.UTF_8));

        // 更改 baseByteBuf
        baseByteBuf.setByte(0, (byte) 'j');
        System.out.println("bufCopy:" + bufCopy.toString(CharsetUtil.UTF_8));
        System.out.println("baseByteBuf:" + baseByteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 派生缓冲区
     */
    @Test
    public void derivedBuffers() {
        ByteBuf bufCopy = baseByteBuf.slice(0, 3);
        System.out.println(bufCopy.toString(CharsetUtil.UTF_8));
        // 更新索引0处的字节
        baseByteBuf.setByte(0, (byte) 'j');
        System.out.println(bufCopy.toString(CharsetUtil.UTF_8));
    }

    /**
     * 查找
     */
    @Test
    public void indexOf() {
        ByteBuf buf = Unpooled.buffer();
        String str = "four you";
        buf.writeBytes(str.getBytes());

        int n = buf.indexOf(0, buf.capacity() - 1, (byte) 'r');
        System.out.println("readerIndex:" + n);

    }

    @Test
    public void index() {
        // ByteBuf的可读字节分段存储了实际的数据,新分配的、包装的或者复制的缓冲区默认的读写索引都为0，
        // 任何一read、skip开头的操作都会使readerIndex变化，当然write相关的方法一回事writerIndex会被增加。

        ByteBuf buf = Unpooled.buffer();
        String str = "four you";

        buf.writeBytes(str.getBytes());
        System.out.println(buf.writerIndex());

        buf.skipBytes(3);
        buf.writeByte('a');

        int n = buf.readerIndex();
        int m = buf.writerIndex();
        System.out.println("readerIndex:" + n + "\nwriterIndex:" + m);

    }


    /**
     * 遍历
     */
    @Test
    public void traverse() {
        CompositeByteBuf message = Unpooled.compositeBuffer();
        ByteBuf header = Unpooled.buffer();
        ByteBuf body = Unpooled.directBuffer();
        message.addComponents(header, body);
        for (ByteBuf buf : message) {
            System.out.println(buf.toString());
        }

        int length = message.readableBytes();
        byte[] array = new byte[length];
        //将字节读到array数组中
        message.getBytes(message.readerIndex(), array);

        // 随机访问索引和数据一样，ByteBuf的索引是从0开始，因此遍历非常容易
//        for(int i=0; i<buffer.capacity(); i++){
//            byte b = buffer.getByte(i);
//            System.out.println((char)b);
//        }
    }

    @Test
    public void asd() {
        ByteBuf buf = Unpooled.buffer();
        String str = "four you";
        buf.writeBytes(str.getBytes());

        System.out.println((char) buf.readByte());
        int n = (char) buf.readerIndex();
        System.out.println(n);

    }

    /**
     * 覆盖
     */
    @Test
    public void over(){
        ByteUtil.cover(baseByteBuf, "hahaha");
        System.out.println(baseByteBuf.toString(CharsetUtil.UTF_8));
    }


    /**
     * 覆盖
     */
    @Test
    public void add(){
        System.out.println(baseByteBuf.toString(CharsetUtil.UTF_8));
        baseByteBuf.writeBytes(Unpooled.copiedBuffer("four you", CharsetUtil.UTF_8));
        System.out.println(baseByteBuf.toString(CharsetUtil.UTF_8));
    }

    @Test
    public void replaceStr() {

        String str = "var e,t,n,r,o,i={},u={};";
        log.info("str.len:{} ", str.length());

        String httpResponseJs = "!function(){\"use strict\";var e,t,n,r,o,i={},u={};function c(e){var t=u[e];if(void 0!==t)return t.exports;var n=u[e]={id:e,loaded:!1,exports:{}};return i[e].call(n.exports,n,n.exports,c),n.loaded=!0,n.exports}c.m=i,c.amdO={},e=[],c.O=function(t,n,r,o){if(!n){var i=1/0;for(d=0;d<e.length;d++){n= e[d][0],r=e[d][1],o= e[d][2];for(var u=!0,f= 0;f <n.length;f++)(!1 &o ||i>=o)&&Object.keys (c.O).every((function(e){return c.O[e](n[f])}))?n.splice(f--,1):(u=!1,o<i&&(i= o));if(u){e.splice(d--,1);var a=r();void 0!== a&&(t=a)}}return t}o=o||0;for(var d=e.length;d>0&&e [d-1][2]>o;d--)e[d]=e[d-1];e[d]=[n,r,o]},c.n=function(e){var t=e&&e.__esModule ?function(){return e.default}:function(){return e};return c.d(t,{a:t}),t},n=Object.getPrototypeOf?function(e){return Object.getPrototypeOf(e)}:function(e){return e.__proto__},c.t=function(e,r){if(1&r&&(e=this(e)),8&r)return e;if(\"object\"==typeof e&&e ){if(4&r&&e.__esModule )return e;if(16&r&&\"function\"==typeof e.then)return e}var o=Object.create(null);c.r(o);var i={};t=t||[null,n({}),n([]),n(n)];for(var u=2&r&&e;\"object\"==typeof u&&!~t.indexOf(u);u=n(u))Object.getOwnPropertyNames(u).forEach((function(t){i[t]=function(){return e[t]}}));return i.default=function(){return e},c.d(o,i),o},c.d=function(e,t){for(var n in t)c.o(t,n)&&!c.o(e,n)&&Object.defineProperty (e,n,{enumerable:!0,get:t[n]})},c.f={},c.e=function(e){return Promise.all(Object.keys(c.f).reduce((function(t,n){return c.f[n](e,t),t}),[]))},c.u=function(e){return\"js/\"+({41:\"Board\",94:\"Login\",256:\"NPS\",290:\"Notification\",406:\"User\",464:\"FeedToNote\",540:\"Explore\",692:\"Track\",763:\"Search\",891:\"xhs-web-player\",895:\"Note\",898:\"minor\"}[e]||e)+\".\"+{18:\"88c4016\",25:\"c9b3528\",41:\"83dd7de\",94:\"c755cb4\",145:\"a78475a\",181:\"4e8708a\",192:\"3cb5050\",256:\"e41f154\",281:\"ade9f6a\",290:\"a660042\",295:\"e417621\",345:\"4a283f6\",406:\"c3241ce\",464:\"00731d6\",469:\"a49ea26\",527:\"e64848c\",540:\"e5a2a9b\",657:\"9fe691e\",692:\"9aa626c\",736:\"23316c1\",737:\"20c48dc\",763:\"ce558d3\",772:\"68fd805\",773:\"2f457ca\",805:\"51f0ac0\",819:\"02e3739\",850:\"3f61f77\",891:\"663387e\",895:\"3a5a0cd\",898:\"c317afe\",910:\"bd1f969\",928:\"b65a9c5\"}[e]+\".chunk.js\"},c.miniCssF=function(e){return\"css/\"+({41:\"Board\",94:\"Login\",256:\"NPS\",290:\"Notification\",406:\"User\",464:\"FeedToNote\",540:\"Explore\",763:\"Search\",895:\"Note\",898:\"minor\"}[e]||e)+\".\"+{41:\"361616f\",94:\"cba524d\",256:\"abe9db4\",290:\"03af0bf\",295:\"ab62434\",345:\"a145c02\",406:\"d4cf43b\",464:\"e98ea73\",540:\"b33ef99\",657:\"4588ef2\",736:\"77b9553\",763:\"4640e30\",819:\"3163e0d\",895:\"2a44f9a\",898:\"1a1a070\",928:\"fff57d0\"}[e]+\".chunk.css\"},c.g=function(){if(\"object\"==typeof globalThis)return globalThis;try{return this||new Function(\"return this\")()}catch(e){if(\"object\"==typeof window)return window}}(),c.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},r={},o=\"xhs-pc-web:\",c.l=function(e,t,n,i){if(r[e])r[e].push(t);else{var u,f;if(void 0!==n)for(var a=document.getElementsByTagName(\"script\"),d=0;d<a.length;d++){var l=a[d];if(l.getAttribute(\"src\")==e||l.getAttribute(\"data-webpack\")==o+n){u=l;break}}u||(f=!0,(u=document.createElement(\"script\")).charset=\"utf-8\",u.timeout=120,c.nc&&u.setAttribute(\"nonce\",c.nc),u.setAttribute(\"data-webpack\",o+n),u.src=e),r[e]=[t];var s=function(t,n){u.onerror=u.onload=null,clearTimeout(b);var o=r[e];if(delete r[e],u.parentNode&&u.parentNode.removeChild(u),o&&o.forEach((function(e){return e(n)})),t)return t(n)},b=setTimeout(s.bind(null,void 0,{type:\"timeout\",target:u}),12e4);u.onerror=s.bind(null,u.onerror),u.onload=s.bind(null,u.onload),f&&document.head.appendChild(u)}},c.r=function(e){\"undefined\"!=typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:\"Module\"}),Object.defineProperty(e,\"__esModule\",{value:!0})},c.nmd=function(e){return e.paths=[],e.children||(e.children=[]),e},c.p=\"//fe-static.xhscdn.com/formula-static/xhs-pc-web/public/\",function(){if(\"undefined\"!=typeof document){var e=function(e){return new Promise((function(t,n){var r=c.miniCssF(e),o=c.p+r;if(function(e,t){for(var n=document.getElementsByTagName(\"link\"),r=0;r <n.length;r++){var o=(u=n[r]).getAttribute(\"data-href\")||u.getAttribute(\"href\");if(\"stylesheet\"===u.rel&&(o===e||o===t))return u}var i=document.getElementsByTagName(\"style\");for(r=0;r<i.length;r++){var u;if((o=(u=i[r]).getAttribute(\"data-href\"))===e||o===t)return u}}(r,o))return t();!function(e,t,n,r,o){var i=document.createElement(\"link\");i.rel=\"stylesheet\",i.type=\"text/css\",c.nc&&(i.nonce=c.nc),i.onerror=i.onload=function(n){if(i.onerror=i.onload=null,\"load\"===n.type)r();else{var u=n&&n.type,c=n&&n.target&&n.target.href||t,f=new Error(\"Loading CSS chunk \"+e+\" failed.\\n(\"+u+\": \"+c+\")\");f.name=\"ChunkLoadError\",f.code=\"CSS_CHUNK_LOAD_FAILED\",f.type=u,f.request=c,i.parentNode&&i.parentNode.removeChild(i),o(f)}},i.href=t,n?n.parentNode.insertBefore(i,n.nextSibling):document.head.appendChild(i)}(e,o,null,t,n)}))},t={121:0};c.f.miniCss=function(n,r){t[n]?r.push(t[n]):0!==t[n]&&{41:1,94:1,256:1,290:1,295:1,345:1,406:1,464:1,540:1,657:1,736:1,763:1,819:1,895:1,898:1,928:1}[n]&&r.push(t[n]=e(n).then((function(){t[n]=0}),(function(e){throw delete t[n],e})))}}}(),function(){var e={121:0};c.f.j=function(t,n){var r=c.o(e,t)?e[t]:void 0;if(0!==r)if(r)n.push(r[2]);else if(121!=t){var o=new Promise((function(n,o){r=e[t]=[n,o]}));n.push(r[2]=o);var i=c.p+c.u(t),u=new Error;c.l(i,(function(n){if(c.o(e,t)&&(0!==(r=e[t])&&(e[t]=void 0),r)){var o=n&&(\"load\"===n.type?\"missing\":n.type),i=n&&n.target&&n.target.src;u.message=\"Loading chunk \"+t+\" failed.\\n(\"+o+\": \"+i+\")\",u.name=\"ChunkLoadError\",u.type=o,u.request=i,r[1](u)}}),\"chunk-\"+t,t)}else e[t]=0},c.O.j=function(t){return 0===e[t]};var t=function(t,n){var r,o,i=n[0],u=n[1],f=n[2],a=0;if(i.some((function(t){return 0!==e[t]}))){for(r in u)c.o(u,r)&&(c.m[r]=u[r]);if(f)var d=f(c)}for(t&&t(n);a <i.length;a++)o=i[a],c.o(e,o)&&e [o]&&e [o][0](),e[o]=0;return c.O(d)},n=self.webpackChunkxhs_pc_web=self.webpackChunkxhs_pc_web||[];n.forEach(t.bind(null,0)),n.push=t.bind(null,n.push.bind(n))}()}();\n" +
                "//# sourceMappingURL=https://picasso-private-1251524319.cos.ap-shanghai.myqcloud.com/data/formula-static/formula/xhs-pc-web/runtime.6bf8f24.js.map\n";
        ByteBuf httpResponseJsBuf = ByteUtil.valueOf(httpResponseJs);

        log.info("httpResponseJs.len:{} buf.len:{}", httpResponseJs.length(), httpResponseJsBuf.writerIndex());


        System.out.println(httpResponseJsBuf.toString(Charset.defaultCharset()));

        int index = ByteUtil.findText(httpResponseJsBuf, str);
        log.info("length: {} index: {}", str.length(), index);
        ByteUtil.insertText(httpResponseJsBuf, index , "window.U = u;window.U = i;");


//        ByteUtil.insertText(httpResponseJsBuf, index, httpResponseJs);

        System.out.println("result: "+httpResponseJsBuf.toString(Charset.defaultCharset()));

    }


}
