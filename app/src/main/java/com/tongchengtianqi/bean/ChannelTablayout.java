package com.tongchengtianqi.bean;

import java.util.List;

/**
 * Created by 111 on 2017/7/28.
 */

public class ChannelTablayout {


    /**
     * data : {"channels":[{"cid":1,"cname":"推荐"},{"cid":2,"cname":"视频"},{"cid":5,"cname":"社会"},{"cid":7,"cname":"娱乐"},{"cid":3,"cname":"国内"},{"cid":30,"cname":"国际"},{"cid":8,"cname":"健康"},{"cid":32,"cname":"军事"},{"cid":4,"cname":"体育"},{"cid":33,"cname":"时尚"},{"cid":11,"cname":"汽车"},{"cid":10,"cname":"科技"},{"cid":34,"cname":"逗比"},{"cid":9,"cname":"图片"},{"cid":6,"cname":"财经"},{"cid":39,"cname":"星座"},{"cid":40,"cname":"动漫"},{"cid":21,"cname":"游戏"},{"cid":36,"cname":"旅游"},{"cid":22,"cname":"互联网"},{"cid":20,"cname":"数码"},{"cid":35,"cname":"干货"},{"cid":27,"cname":"历史"},{"cid":29,"cname":"奇闻"},{"cid":18,"cname":"健身"},{"cid":17,"cname":"美容瘦身"},{"cid":12,"cname":"CBA"},{"cid":13,"cname":"股票"},{"cid":14,"cname":"NBA"},{"cid":15,"cname":"家居"},{"cid":16,"cname":"留学"},{"cid":19,"cname":"商业"},{"cid":23,"cname":"育儿"},{"cid":24,"cname":"房产"},{"cid":25,"cname":"教育"},{"cid":26,"cname":"国内足球"},{"cid":28,"cname":"国际足球"},{"cid":31,"cname":"闺房"},{"cid":37,"cname":"美食"},{"cid":38,"cname":"收藏"}]}
     * status : 0
     */

    private DataBean data;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        private List<ChannelsBean> channels;

        public List<ChannelsBean> getChannels() {
            return channels;
        }

        public void setChannels(List<ChannelsBean> channels) {
            this.channels = channels;
        }

        public static class ChannelsBean {
            /**
             * cid : 1
             * cname : 推荐
             */

            private int cid;
            private String cname;

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public String getCname() {
                return cname;
            }

            public void setCname(String cname) {
                this.cname = cname;
            }
        }
    }
}
