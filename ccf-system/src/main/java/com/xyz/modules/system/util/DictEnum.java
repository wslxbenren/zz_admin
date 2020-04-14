package com.xyz.modules.system.util;

/**
 * 字典
 */
public enum DictEnum {
     EMPTY(0L, ""),
     XING_BIE(100, "性别"),
     MIN_ZU(101, "民族"),
     ZZMM(102, "政治面貌"),
     JI_BIE(103, "级别"),
     ZJDM(104, "证件代码"),
     XUE_LI(105, "学历"),
     ADDRESS(106, "所在地/发生地/户籍地"),
     HYZK(107, "婚姻状况"),
     ZJXY(108, "宗教信仰"),
     YHZGX(109, "与户主关系"),
     GJ_DQ(110, "国籍\\(地区\\)"),
     AJLB(111, "案件类别"),
     XDHG(112, "吸毒后果"),
     XDYY(113, "吸毒原因"),
     ZYLB(114, "职业类别"),
     JGLX(201, "机构类型"),
     JGCJ(202, "机构层级/组织层级/综治中心层级/地区层级/实施主体层级"),
     WXCD(203, "危害程度"),
     XXBXLX(204, "学校办学类型"),
     AJXZ(205, "案（事）件性质"),
     ZAYHDJ(206, "治安隐患等级"),
     LXLX(207, "路线类型"),
     HJFS(208, "化解方式"),
     ZYDSRRYLB(209, "主要当事人人员类别"),
     SJLB(210, "事件类别"),
     SJGM(211, "事件规模"),
     QYLX(212, "企业类型"),
     JYFW(213, "经营范围"),
     KGQK(214, "控股情况"),
     DJZCLX(215, "登记注册类型"),
     XGPG(216, "效果评估"),
     SJQYLX(217, "涉及区域类型"),
     ZATCWT(218, "治安突出问题"),
     GZCD(219, "关注程度"),
     SHZZLX(220, "社会组织类型"),
     AQYHLX(221, "安全隐患类型"),
     QYLB(222, "企业类别"),
     BFSD(223, "帮扶手段"),
     JTQK(224, "家庭情况"),
     RYLX(225, "人员类型"),
     SZQK(226, "收治情况"),
     GZLX(227, "关注类型"),
     GRTJ(228, "感染途径"),
     GKQK(229, "管控情况"),
     BFQK(230, "帮扶情况"),
     CYGLRY(231, "参与管理人员"),
     SSZYZLYY(232, "实施住院治疗原因"),
     ZLQK(233, "治疗情况"),
     MQWXXPGDJ(234, "目前危险性评估等级"),
     MQZDLX(235, "目前诊断类型"),
     JTJJZK(236, "家庭经济状况"),
     JZJCLX(237, "矫正解除（终止）类型"),
     JZXZRYZCQK(238, "矫正小组人员组成情况"),
     SSHEQK(239, "“三涉”情况c"),
     SSHIQK(240, "“四史”情况b"),
     JSFS(241, "接收方式"),
     JZLX(242, "矫正类别"),
     AZQK(243, "安置情况"),
     XJQK(244, "衔接情况"),
     WXXPGLX(245, "危险性评估类型"),
     YZM(246, "原罪名"),
     YHLX(247, "隐患类型"),
     ZW(248, "职务"),
     ZYTC(249, "专业特长"),
     ZZLX(250, "组织类型"),
     ZCZL(251, "政策种类"),
     AJFJ(252, "案（事）件分级"),
     AJLX(253, "案（事）件类型"),
     LRYY(254, "流入原因"),
     BZLX(255, "办证类型"),
     ZSLX(256, "住所类型"),
     JKZK(257, "健康状况"),
     RHYZBS(258, "人户一致标识"),
     LHMD(260, "来华目的"),
     JZYT(261, "建筑用途"),
     CZYT(262, "出租用途"),
     SJZT(263,"数据状态");
    private String distName;
    private long dictId;

    DictEnum(long dictId, String distName) {
        this.dictId = dictId;
        this.distName = distName;
    }

    public String getDistName() {
        return this.distName;
    }

    public long getDictId() {
         return this.dictId;
    }
}
