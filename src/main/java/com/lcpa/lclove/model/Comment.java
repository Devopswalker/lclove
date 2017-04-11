package com.lcpa.lclove.model;

public class Comment {
    private Integer id;

    private Integer articleId;

    private String nickName;

    private String email;

    private String content;

    private String editorReply;

    private Integer commStatus = 1;

    private Integer parentId;

    private Integer upNum = 0;

    private Integer downNum = 0;

    private Integer replyId;  //被回复的评论 ID

    private String replyName; //被回复的评论 人名字

    private String replyContent; //被回复的评论 内容

    private Integer repliedNumber = 0; // 该评论被回复的次数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEditorReply() {
        return editorReply;
    }

    public void setEditorReply(String editorReply) {
        this.editorReply = editorReply == null ? null : editorReply.trim();
    }

    public Integer getCommStatus() {
        return commStatus;
    }

    public void setCommStatus(Integer commStatus) {
        this.commStatus = commStatus;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getUpNum() {
        return upNum;
    }

    public void setUpNum(Integer upNum) {
        this.upNum = upNum;
    }

    public Integer getDownNum() {
        return downNum;
    }

    public void setDownNum(Integer downNum) {
        this.downNum = downNum;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public String getReplyName() {
        return replyName;
    }

    public void setReplyName(String replyName) {
        this.replyName = replyName;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Integer getRepliedNumber() {
        return repliedNumber;
    }

    public void setRepliedNumber(Integer repliedNumber) {
        this.repliedNumber = repliedNumber;
    }
}