var ibmweb=ibmweb||{};ibmweb.dynnav={};ibmweb.dynnav.ccfintercept={};
ibmweb.dynnav.ccfintercept=function(){function s(){if(window.location.host.indexOf(".ibm.com")<0)return console.warn("Intercept survey only with ibm.com"),!1;if(!b.intercept.survey)return console.warn("No intercept survey was defined."),!1;if(!b.intercept.survey.type)return console.warn("No intercept survey type was defined."),!1;if(!b.intercept.survey.id)return console.warn("No intercept survey id was defined."),!1;if(!b.intercept.percent)return console.warn("No intercept percent was defined."),!1;
if(!b.intercept.invitation)return console.warn("No intercept invitation was defined."),!1;var a=k("ccf-global-intercept");if(a!==null){var a=(new Function("return "+a))(),a=new Date(a.date),d=new Date(a.getTime()+b.global_minimum_wait*864E5);console.log("An intercept survey was last seen "+o(a)+".\nThe earliest another one may be seen is "+o(d));return!1}if(k(n)!==null)return console.log("This survey ("+b.intercept.survey.type+":"+b.intercept.survey.id+") has already been seen."),!1;if(!u())return!1;
if(a=typeof b.intercept.referrerURL!=="undefined"&&b.intercept.referrerURL.length>0){a=!1;d=b.intercept.referrerURL;len=d.length;i=-1;for(href=document.referrer;++i<len&&!a;)l(d[i],href)&&(a=!0);a=!a}if(a)return console.warn("Intercept: Referrer URL is not matching."),!1;if(a=typeof b.intercept.excludeURL!=="undefined"&&b.intercept.excludeURL.length>0){a=!1;d=b.intercept.excludeURL;len=d.length;i=-1;for(href=document.referrer;++i<len&&!a;)l(d[i],href)&&(a=!0)}if(a)return!1;if(!v())return!1;a=Math.random()*
100;if(!(a<b.intercept.percent))return console.log("Survey was all ready, but did not pass the "+b.intercept.percent+"% barrier. Current percent is "+a),!1;return!0}function v(){if(b.intercept.interceptDisp=="pageExit"&&b.intercept.surveyDisp!="immediate")b.intercept.surveyDisp="immediate",console.warn("Survey display should be immediate or page exit");if(b.intercept.interceptDisp=="siteExit"&&b.intercept.surveyDisp!="immediate")b.intercept.surveyDisp="immediate",console.warn("Survey display should be immediate");
if(b.intercept.interceptDisp=="siteExit"||b.intercept.surveyDisp=="siteExit")if(typeof b.intercept.exitURL=="undefined"||b.intercept.exitURL.length<=0)return console.warn("Intercept: Exit URL is empty."),!1;else{var a=!1,d=b.intercept.exitURL;len=d.length;i=-1;for(href=document.location.href;++i<len&&!a;)l(d[i],href)&&(a=!0);if(!a)return console.warn("Intercept: Exit URL is not matching."),!1}return!0}function t(a,d){var c=b.intercept.presentDelay*1E3;d=="entry"?setTimeout(function(){overlay.show(a)},
c):d=="exit"&&w(a)}function u(){if(typeof b.intercept.preventCount!=="undefined"){if(isNaN(parseFloat(b.intercept.preventCount))||!isFinite(b.intercept.preventCount))return console.warn("Invalid prevent count."),!1;var a=b.intercept.preventCount,d="ccf-prevent-intercept-"+b.intercept.survey.type+":"+b.intercept.survey.id;k(d)==null&&k(d,1,q);var c=k(d);if(c<a)return c++,k(d,c,q),!1}return!0}function x(){var a="";switch(b.intercept.survey.type){case "asm":var a="",d=b.intercept.survey.metaData;if(d!=
""&&typeof d!="undefined")for(var d=d.split("&"),c=0;c<d.length;c++){var e=d[c].split("=");a+="opdata_"+e[0]+"="+e[1]+"&"}a=a.length>0?a.slice(0,-1):a;a=a.length>0?"&"+a:a;a="//survey.opinionlab.com/survey/s?s="+b.intercept.survey.id+a;break;case "customB":a=b.intercept.survey.url}a===null&&console.warn("Intercept survey type is unknown: "+b.intercept.survey.type);return a}function k(a,b,c){var e=null;if(typeof c!="undefined")switch(typeof c){case "string":e=new Date(c);break;case "number":e=new Date((new Date).getTime()+
c*6E4);break;case "object":e=c}return typeof b=="undefined"?dojo.cookie(a)||null:c?dojo.cookie(a,b,{expires:e,path:"/"}):dojo.cookie(a,b,{path:"/"})}function o(a){if(a===null)return null;var b="",a=new Date(a),c=new Date;if(!(c.getFullYear()==a.getFullYear()&&c.getMonth()==a.getMonth()&&c.getDate()==a.getDate())){var c=a.getFullYear(),e=a.getMonth()+1,e=(e<10?"0":"")+e,f=a.getDate();b+=e+"/"+((f<10?"0":"")+f)+"/"+c;b+=" "}c=a.getHours();e=a.getMinutes();a=c>12?"pm":"am";b+=(c+11)%12+1+":"+((e<10?
"0":"")+e)+a;return b}function y(a,b){for(var c,e=/<([^>\s]*)[^>]*>/g,f=[],g=0,h="";(c=e.exec(a))&&b;)g=a.substring(g,c.index).substr(0,b),h+=g,b-=g.length,g=e.lastIndex,b&&(h+=c[0],c[1].indexOf("/")===0?f.pop():c[1].lastIndexOf("/")!==c[1].length-1&&f.push(c[1]));for(h+=a.substr(g,b);f.length;)h+="</"+f.pop()+">";h.length>400&&(h=h.substring(0,h.lastIndexOf(" "))+"...");return h}function l(a,b){var c=!1;(match=b.match(RegExp("^"+a+"$","i")))&&match.index===0&&(c=!0);return c}function z(){for(var a=
!1,d=window.location.href,c=0;c<b.exception.data.length;c++){var e=b.exception.data[c];if(e["for"]=="percentage"&&typeof e.limit!=="undefined"&&e.limit>=b.intercept.percent){var a=e,e=d,f=!1;if(a.type=="URL")if(typeof a.value==="string"&&l(a.value,e))f=!0,exception_for=a["for"];else for(var g=0;g<a.value.length;g++){if(l(a.value[g],e)){f=!0;exception_for=a["for"];break}}else if(a.type=="ID")if(typeof a.value==="string"&&l(a.value,e))f=b.intercept.survey.id==a.value,exception_for=a["for"];else for(g=
0;g<a.value.length;g++)if(l(a.value[g],e)){f=b.intercept.survey.id==a.value[g];exception_for=a["for"];break}if(a=f)break}}return a}var b,n,r,q,p=!1;Array.isArray=function(a){return Object.prototype.toString.call(a)==="[object Array]"};ibmweb.opinionlab=ibmweb.opinionlab||{};var w=function(a){function d(){dojo.query(l).onclick(function(a){var b=a.currentTarget.parentElement.parentElement;if(a.currentTarget.href!=""&&a.currentTarget.href.substr(0,1)!="#"&&b.id!="ibm-menu-links")a.preventDefault(),j.target=
a.currentTarget.target,j.url=a.currentTarget.href,f()});dojo.query(A).onclick(function(a){if(a.currentTarget.href!=""&&a.currentTarget.href.substr(0,1)!="#")a.preventDefault(),j.target=a.currentTarget.target,j.url=a.currentTarget.href,f()});dojo.query(B).onclick(function(a){if(a.currentTarget.href!=""&&a.currentTarget.href.substr(0,1)!="#")a.preventDefault(),j.url=a.currentTarget.href,j.target=a.currentTarget.target,f()});dojo.query(o).onclick(function(){f()});dojo.query("[data-action='opt-out']",
dojo.byId("sp-intercept-overlay-invitation")).onclick(function(){window.onbeforeunload=null});dojo.query("[data-action='opt-in']",dojo.byId("sp-intercept-overlay-invitation")).onclick(function(){m=!0})}function c(){r='{"date": '+(new Date).getTime()+',"type": "'+b.intercept.survey.type+'","id": "'+b.intercept.survey.id+'"}';ibmStats.event({ibmEV:"ol survey",ibmEvGroup:b.intercept.survey.type,ibmEvModule:b.intercept.survey.id,ibmEvAction:"intercept shown"});k("ccf-global-intercept",r,new Date((new Date).getTime()+
b.global_minimum_wait*864E5));console.log("Creating cookies for intercept blocking: ccf-global-intercept, "+n);k(n,r,q);setTimeout(function(){typeof dijit.byId("dialog_sp-intercept-overlay-invitation")==="undefined"?c():(dojo.connect(dijit.byId("dialog_sp-intercept-overlay-invitation"),"onHide",function(){window.onbeforeunload=null}),p&&(m=!0),m&&g(),dojo.query("[data-action='opt-out']",dojo.byId("sp-intercept-overlay-invitation")).onclick(function(){window.onbeforeunload=null}),dojo.query("[data-action='opt-in']",
dojo.byId("sp-intercept-overlay-invitation")).onclick(function(){m=!0}))},50)}function e(){setTimeout(function(){typeof dijit.byId("dialog_sp-intercept-overlay-survey")==="undefined"?e():dojo.connect(dijit.byId("dialog_sp-intercept-overlay-survey"),"onHide",function(){console.log("fdf");g()})},50)}function f(){s()==!1&&!p?(m=!0,g()):(overlay.show(a),c(),e(),h(),setTimeout(function(){if(typeof dijit.byId("dialog_sp-intercept-overlay-survey")!="undefined"&&!dijit.byId("dialog_sp-intercept-overlay-survey").open)m=
!1,g();else return!1},2E3),p=!1)}function g(){if(!m)m=!0,window.onbeforeunload=null,j.target==""?window.location.href=j.url:window.open(j.url,j.target)}function h(){setTimeout(function(){window.onbeforeunload=function(){return!1}},500)}var l="#ibm-masthead a",A="#ibm-footer-module a",B="a.ibm-intercept-exit-survey",o="span.ibm-intercept-exit-survey",m=!1,j={url:""};Array.prototype.diff=function(a){return this.filter(function(b){return a.indexOf(b)<0})};dojo.ready(function(){setTimeout(d,2E3)});return{status:"loaded"}};
return{callback_whitelist:function(a){b.exception={};b.exception.data=a;b.whitelist_status=!0},initIntercept:function(){var a=opinionlab.intercept;if(typeof a=="undefined")return!1;b=dojo.mixin({whitelist_status:null,url_whitelist_path:"//www.ibm.com/common/scripts/ccf/ccf-intercept-whitelist.js",global_minimum_wait:30,now:new Date,content_limit:400,OLcardObj:null,watermarkImage:'<div style="background: url(data:image/png;base64,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) no-repeat scroll 0px 0px transparent; height: 136px; width: 135px; z-index: 9999; position: absolute;">&nbsp;</div>',
defaults:{customVariables:{},emailInfoLoaded:!1},intercept:dojo.mixin({interceptDisp:"pageLoad",surveyDisp:"immediate",anon:"true",anonData:{userName:"",userEmail:"",emailInfoLoaded:!0},percent:26,start:"Jan 01 2014",stop:"Dec 31 2099",presentDelay:3,invitation:{title:"Tell IBM what you think",content:"Would you please take 1 minute to take this survey?",yes_label:"Yes",no_label:"No thanks"},survey:{type:"asm",id:"11320",width:533,height:417,metaData:""}},a)});n="ccf-"+b.intercept.survey.type+":"+
b.intercept.survey.id;dojo.io.script.get({url:b.url_whitelist_path});setTimeout(function(){b.whitelist_status=!0},800);var d=b.intercept.invitation,c="entry";setTimeout(function(){if(s()){if(b.intercept.survey.type!="asm"&&b.intercept.survey.type!="customB")return!1;typeof ibmStats=="undefined"&&dojo.create("script",{src:"//www.ibm.com/common/stats/stats.js",type:"text/javascript"},dojo.query("head")[0]);console.log("Launching the intercept...");b.intercept.survey.url=x();b.intercept.interceptDisp!=
"pageLoad"&&(c="exit");var a="",a='<div id="sp-intercept-overlay-invitation" class="ibm-common-overlay" style="display: none;" class="ibm-common-overlay">\n\t<div class="ibm-head">\n\t\t<p><a class="ibm-common-overlay-close" href="#close">Close [x]</a></p>\n\t</div>\n\t<div class="ibm-body">\n\t\t\n\t\t{{watermark}}\n\t\t<div class="ibm-main">\n\t\t\t<a class="ibm-access"><\!-- Accessibility anchor --\></a>\n\t\t\t\n\t\t\t<div class="overlay-content-slide active" data-slide="main">\n\t\t\t\t<div class="ibm-title">\n\t\t\t\t\t<h2>{{title}}</h2>\n\t\t\t\t</div>\n\t\t\t\t<div class="ibm-container ibm-alternate" style="">\n\t\t\t\t\t<div class="ibm-container-body">\n\t\t\t\t\t\t<p>{{content}}</p>\n\t\t\t\t\t\n\t\t\t\t\t\t{{anonymity}}\n\t\t\t\t\t</div>\n\t\t\t\t</div>\n\t\t\t\t<div class="ibm-buttons-row" style="text-align: right;">\n\t\t\t\t\t<div class="ibm-rule"><hr /></div>\n\t\t\t\t\t<a href="javascript:;" role="button" aria-label="Yes" class="ibm-btn-pri" data-action="opt-in">{{yes_label}}</a>\n\t\t\t\t\t&nbsp;&nbsp;\n\t\t\t\t\t<a href="javascript:;" role="button" aria-label="No" class="ibm-btn-sec" data-action="opt-out">{{no_label}}</a>\n\t\t\t\t\t&nbsp;&nbsp;\n\t\t\t\t</div>\n\t\t\t\t\n\t\t\t\t\t<a href="javascript:;" id="aboutFeedback" aria-label="About feedback" data-action="change-slide" data-slide-target="about">About feedback at IBM</a>\n\t\t\t\t\n\t\t\t</div>\n\n\t\t\t<div class="overlay-content-slide" style="display: none;" data-slide="about">\n\t\t\t\t<div class="ibm-title">\n\t\t\t\t\t<h2>Ongoing Web Feedback at IBM</h2>\n\t\t\t\t</div>\n\t\t\t\t<div class="ibm-container ibm-alternate" style="">\n\t\t\t\t\t<div class="ibm-container-body">\n\t\t\t\t\t\t<p>\n\t\t\t\t\t\t\tIBM collects opt-in feedback from IBM web users on a broad and continual basis throughout it\'s web sites.\n\t\t\t\t\t\t\tAll feedback submitted are reviewed only by IBM employees or IBM affiliates and no feedback is shared outside\n\t\t\t\t\t\t\tof IBM for any reason. See IBM\'s <a href="http://www.ibm.com/privacy/us/en/?lnk=flg-priv-usen" target="_ibm_privacy_policy" data-action="close-overlay">privacy policy</a> and <a href="http://www.ibm.com/legal/us/en/?lnk=flg-tous-usen" data-action="close-overlay" target="_ibm_tou">terms of use</a> for further detail.\n\t\t\t\t\t\t</p>\n\n\t\t\t\t\t\t<p>\n\t\t\t\t\t\t\tIBM may use a third party to collect or process feedback. Any such party is also bound by the IBM policy.\n\t\t\t\t\t\t\tCurrently we use opinionlab for feedback processing.\n\t\t\t\t\t\t\t<br />\n\t\t\t\t\t\t\t<a href="javascript:;" aria-label="OpinionLab" class="ibm-external-link" data-action="change-slide" data-slide-target="leaving">opinionlab</a>\n\t\t\t\t\t\t</p>\n\n\t\t\t\t\t\t<p>\n\t\t\t\t\t\t\tYour input is very valuable to us and although we read every comment that is sent to IBM in an effort to continuously\n\t\t\t\t\t\t\timprove our web sites for you, we generally do not reply to comments from this system unless otherwise stated.\n\t\t\t\t\t\t\tThank you for participating in the IBM web feedback program.\n\t\t\t\t\t\t</p>\n\t\t\t\t\t</div>\n\t\t\t\t</div>\n\t\t\t\t<div class="ibm-buttons-row">\n\t\t\t\t\t<div class="ibm-rule"><hr /></div>\n\t\t\t\t\t<a href="javascript:;" id="backToFeedback" role="button" aria-label="Back to feedback" class="ibm-btn-sec" data-action="change-slide" data-slide-target="main">Back to Feedback</a>\n\t\t\t\t</div>\n\t\t\t</div>\n\n\n\t\t\t<div class="overlay-content-slide" style="display: none;" data-slide="leaving">\n\t\t\t\t<div class="ibm-title">\n\t\t\t\t\t<h2>Leaving the IBM Web Site</h2>\n\t\t\t\t</div>\n\t\t\t\t<div class="ibm-container ibm-alternate ibm-buttons-last" style="">\n\t\t\t\t\t<div class="ibm-container-body">\n\t\t\t\t\t\t<p>\n\t\t\t\t\t\t\tYou are now leaving the IBM Web site. IBM makes no representations or warranties about any other Web site which\n\t\t\t\t\t\t\tyou may access through this one. When you access non-IBM Web sites, even though they might contain the IBM logo\n\t\t\t\t\t\t\tand content regarding IBM\'s products and services, such Web sites are independent of IBM and IBM has no control\n\t\t\t\t\t\t\tover the operation of non-IBM Web sites. In addition, a link to a non-IBM Web site does not mean that IBM endorses\n\t\t\t\t\t\t\tthat Web site or has any responsibility for the use of such Web site.\n\t\t\t\t\t\t</p>\n\t\t\t\t\t</div>\n\t\t\t\t</div>\n\t\t\t\t<div class="ibm-buttons-row">\n\t\t\t\t\t<div class="ibm-rule"><hr /></div>\n\t\t\t\t\t<a href="//secure.opinionlab.com/about_this_system.html" target="_about_opinion_lab" class="ibm-btn-pri" data-action="change-slide" data-slide-target="main" style="float: right; display: inline;">Continue</a>\n\t\t\t\t\t<a href="javascript:;" role="button" aria-label="Cancel" class="ibm-btn-sec" data-action="change-slide" data-slide-target="main">Cancel</a>\n\t\t\t\t</div>\n\t\t\t</div>\n\t\t</div>\n\t</div>\n\t<div class="ibm-footer"></div>\n</div>\n\n\n<div id="sp-intercept-overlay-survey" style="width: {{survey_width}}px;display: none;">\n\t<div class="ibm-head">\n\t\t<p><a class="ibm-common-overlay-close" href="#close">Close [x]</a></p>\n\t</div>\n\t<div class="ibm-body">\n\t\t<div class="ibm-main" style="padding: 0;">\n\t\t\t<a class="ibm-access"><\!-- Accessibility anchor --\></a>\n\t\t\t<div class="ibm-title" style="display: none;">\n\t\t\t\t<h2>Survey!!!</h2>\n\t\t\t</div>\n\t\t\t<div class="ibm-container ibm-alternate" style="padding: 0;">\n\t\t\t\t<div class="ibm-container-body" id="_interceptDialog">\n\t\t\t\t\t<iframe title="Knowledge Center survey" id="_surveyIframe" src="{{survey_url}}" width="{{survey_width}}" height="{{survey_height}}" frameborder="0"></iframe>\n\t\t\t\t</div>\n\t\t\t</div>\n\t\t</div>\n\t</div>\n\t<div class="ibm-footer"></div>\n</div>'.split("{{title}}").join(d.title).split("{{content}}").join(y(d.content,
b.content_limit)).split("{{yes_label}}").join(d.yes_label).split("{{no_label}}").join(d.no_label).split("{{survey_url}}").join(b.intercept.survey.url).split("{{survey_width}}").join(b.intercept.survey.width).split("{{survey_height}}").join(b.intercept.survey.height).split("{{anonymity}}").join('<p style="padding-bottom: 3px;padding-top: 8px;">Your feedback is anonymous</p>').split("{{watermark}}").join(b.intercept.percent>25&&!z()?b.watermarkImage:"");a+='<style type="text/javascript">div.ibm-common-overlay div.overlay-content-slide { display: none; } div.ibm-common-overlay div.overlay-content-slide.active { display: block; }<\/script>';
try{a.match(/class="ibm-common-overlay" id="[^"]*"/g),dojo.place(a,dojo.query("body")[0],"last"),window.addEventListener("message",function(a){a.data=="closeSurvey"&&(overlay.hide("sp-intercept-overlay-survey"),a=dojo.byId("ibmLogo"),dijit.focus(a))},!1),dojo.query("[data-action]",dojo.byId("sp-intercept-overlay-invitation")).connect("click",function(){actions=dojo.attr(this,"data-action").split(/,[\s]*/g);var a=dojo.attr(this,"data-slide-target");dojo.forEach(actions,function(c){switch(c){case "change-slide":dojo.query("div.overlay-content-slide",
dojo.byId("sp-intercept-overlay-invitation")).style("display","none");dojo.query("div.overlay-content-slide[data-slide='"+a+"']",dojo.byId("sp-intercept-overlay-invitation")).style("display","block");c=Math.max(0,Math.floor((dojo.window.getBox().h-dojo.marginBox(dojo.byId("dialog_sp-intercept-overlay-invitation")).h)/2));dojo.query("#dialog_sp-intercept-overlay-invitation").style("top",c+"px");a=="main"?c=dojo.byId("aboutFeedback"):(a="about",c=dojo.byId("backToFeedback"));dijit.focus(c);break;case "opt-in":typeof ibmStats!=
"undefined"&&ibmStats.event({ibmEV:"ol survey",ibmEvGroup:b.intercept.survey.type,ibmEvModule:b.intercept.survey.id,ibmEvAction:"intercept yes clicked"});b.intercept.surveyDisp=="siteExit"||b.intercept.surveyDisp=="pageExit"?(overlay.hide("sp-intercept-overlay-invitation"),p=!0,t("sp-intercept-overlay-survey","exit")):(overlay.hide("sp-intercept-overlay-invitation"),overlay.show("sp-intercept-overlay-survey"));break;case "opt-out":overlay.hide("sp-intercept-overlay-invitation")}})}),t("sp-intercept-overlay-invitation",
c),dojo.connect(dojo.byId("sp-intercept-overlay-invitation"),"onkeypress",function(a){if(a.charOrCode){var b=a.target,c=null,d=null;a.charOrCode===dojo.keys.TAB&&(d=dijit._getTabNavigable(this),c=d.lowest||d.first||this.domNode,d=d.last||d.highest||c);var e=c==d;if(b==c&&a.shiftKey&&a.charOrCode===dojo.keys.TAB)e||dijit.focus(d),dojo.stopEvent(a);else if(b==d&&a.charOrCode===dojo.keys.TAB&&!a.shiftKey)e||dijit.focus(c),dojo.stopEvent(a);else for(;b;){if(b==this.domNode)if(a.charOrCode==dojo.keys.ESCAPE)this.cancel();
else break;b=b.parentNode}}})}catch(f){console.warn("fail"),console.error(f)}}else return!1},2E3)},clear:function(){k(n,null);return!0}}}();dojo.ready(function(){new ibmweb.dynnav.ccfintercept.initIntercept});
