(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5d44478d"],{7514:function(t,e,a){"use strict";var n=a("5ca1"),r=a("0a49")(5),s="find",i=!0;s in[]&&Array(1)[s]((function(){i=!1})),n(n.P+n.F*i,"Array",{find:function(t){return r(this,t,arguments.length>1?arguments[1]:void 0)}}),a("9c6c")(s)},"982e":function(t,e,a){"use strict";a.d(e,"c",(function(){return r})),a.d(e,"b",(function(){return s})),a.d(e,"d",(function(){return i})),a.d(e,"a",(function(){return o}));var n=a("b775");function r(t){return Object(n["a"])({url:"/admin/grade/getgrades",method:"post",data:t})}function s(t){return Object(n["a"])({url:"/admin/grade/del",method:"post",data:t})}function i(t){return Object(n["a"])({url:"/admin/grade/update",method:"post",data:t})}function o(t){return Object(n["a"])({url:"/admin/grade/add",method:"post",data:t})}},c4bc:function(t,e,a){"use strict";var n=a("dd1d"),r=a.n(n);r.a},dd1d:function(t,e,a){},f24e:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("el-row",{attrs:{gutter:24}},[a("el-col",{attrs:{span:10}},[a("el-card",{staticClass:"box-card"},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("span",[t._v("添加年级")]),t._v(" "),a("el-button",{staticStyle:{float:"right",padding:"3px 0"},attrs:{type:"text"},on:{click:function(e){return t.resetForm()}}},[t._v("清空")])],1),t._v(" "),a("div",{staticClass:"text item"},[a("el-form",{ref:"grade",attrs:{"label-position":"top","label-width":"80px",model:t.grade}},[a("el-form-item",{attrs:{label:"年级名称"}},[a("el-input",{model:{value:t.grade.g_name,callback:function(e){t.$set(t.grade,"g_name",e)},expression:"grade.g_name"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"优先级"}},[a("el-input",{model:{value:t.grade.g_priority,callback:function(e){t.$set(t.grade,"g_priority",e)},expression:"grade.g_priority"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"所属学校"}},[a("el-select",{attrs:{placeholder:"请选择学校"},model:{value:t.grade.s_id,callback:function(e){t.$set(t.grade,"s_id",e)},expression:"grade.s_id"}},t._l(t.shools,(function(t,e){return a("el-option",{key:e,attrs:{label:t.s_name,value:t.s_id}})})),1)],1),t._v(" "),a("el-button",{attrs:{type:"primary",loading:t.loading},on:{click:t.addAndUpdate}},[t._v(t._s(t.add?"添加":"更新"))])],1)],1)])],1),t._v(" "),a("el-col",{attrs:{span:14}},[a("el-card",{staticClass:"box-card"},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("el-row",{staticClass:"ctlyt-line-h",attrs:{gutter:10}},[a("el-col",{attrs:{span:8}},[a("span",[t._v("所有年级")])]),t._v(" "),a("el-col",{attrs:{span:8}},[a("el-select",{attrs:{filterable:"",placeholder:"请选择学校"},on:{change:t.shoolChange},model:{value:t.page.s_id,callback:function(e){t.$set(t.page,"s_id",e)},expression:"page.s_id"}},[a("el-option",{key:"shool",attrs:{label:"所有学校",value:0}}),t._v(" "),t._l(t.shools,(function(t,e){return a("el-option",{key:e,attrs:{label:t.s_name,value:t.s_id}})}))],2)],1),t._v(" "),a("el-col",{attrs:{span:8}},[a("el-button",{staticStyle:{float:"right",padding:"3px 0","line-height":"36px"},attrs:{type:"text"},on:{click:t.mGetGrades}},[t._v("刷新")])],1)],1)],1),t._v(" "),a("div",{staticClass:"text item"},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.grades.filter((function(e){return!t.search||e.g_name.toLowerCase().includes(t.search.toLowerCase())}))}},[a("el-table-column",{attrs:{label:"编号",prop:"g_id",width:"60"}}),t._v(" "),a("el-table-column",{attrs:{label:"年级名称",prop:"g_name"}}),t._v(" "),a("el-table-column",{attrs:{label:"优先级",prop:"g_priority"}}),t._v(" "),a("el-table-column",{attrs:{label:"学校名称",prop:"s_name"}}),t._v(" "),a("el-table-column",{attrs:{align:"right",fixed:"right",width:"140"},scopedSlots:t._u([{key:"header",fn:function(e){return[a("el-input",{attrs:{size:"mini",placeholder:"输入关键字搜索"},model:{value:t.search,callback:function(e){t.search=e},expression:"search"}})]}},{key:"default",fn:function(e){return[a("el-button",{attrs:{size:"mini"},on:{click:function(a){return t.handleEdit(e.$index,e.row)}}},[t._v("编辑")]),t._v(" "),[a("el-popconfirm",{attrs:{title:"确定删除"+e.row.g_name+"吗？"},on:{onConfirm:function(a){return t.handleDelete(e.$index,e.row)}}},[a("el-button",{attrs:{slot:"reference",size:"mini",type:"danger"},slot:"reference"},[t._v("删除")])],1)]]}}])})],1),t._v(" "),a("el-pagination",{attrs:{background:"",layout:"prev, pager, next",total:t.page.total,"current-page":t.page.pageNo,"page-size":t.page.pageSize},on:{"prev-click":t.prevClick,"next-click":t.nextClick,"current-change":t.currentChange}})],1)])],1)],1)],1)},r=[],s=(a("7514"),a("ac6a"),a("982e")),i=a("f3fe"),o={name:"Grade",data:function(){return{page:{pageNo:1,pageSize:10,g_name:null,s_id:"",total:0},grade:{g_id:null,g_name:"",g_priority:"",s_id:""},grades:[],shools:[],add:!0,loading:!1,search:"",visible:!1}},created:function(){var t=this;Object(i["c"])({pageNo:1,pageSize:100,s_name:null}).then((function(e){t.shools.length>0&&t.shools.splice(0,t.shools.length),e.data.list.forEach((function(e){t.shools.push(e)}))})).catch((function(){})),this.mGetGrades()},onload:function(){},mounted:function(){},methods:{handleEdit:function(t,e){this.grade=e,this.add=!1},handleDelete:function(t,e){this.visible=!1,this.mDelGrade(e.g_id)},mGetGrades:function(){var t=this;this.resetForm(),Object(s["c"])(this.page).then((function(e){t.grades.length>0&&t.grades.splice(0,t.grades.length),t.page.total=e.data.total,e.data.list.forEach((function(e){e.s_name=t.shools.find((function(t){return t.s_id===e.s_id})).s_name,t.grades.push(e)}))})).catch((function(){}))},mAddGrade:function(){var t=this;Object(s["a"])(this.grade).then((function(e){e.data&&"SUCCESS"===e.code&&(t.$message({message:"添加成功",type:"success"}),t.mGetGrades())})).catch((function(){t.loading=!1}))},mUpdateGrade:function(){var t=this;Object(s["d"])(this.grade).then((function(e){e.data&&"SUCCESS"===e.code&&(t.$message({message:"修改成功",type:"success"}),t.mGetGrades())})).catch((function(){t.loading=!1}))},mDelGrade:function(t){var e=this;Object(s["b"])({g_id:t}).then((function(t){t.data&&t.data>0&&(e.$message({message:"删除成功",type:"success"}),e.mGetGrades())}))},addAndUpdate:function(){this.loading=!0,this.add?this.mAddGrade():this.mUpdateGrade()},resetForm:function(){this.add=!0,this.loading=!1,this.grade={g_id:null,g_name:"",g_priority:"",s_id:""}},prevClick:function(){this.page.pageNo--,this.mGetGrades()},nextClick:function(){this.page.pageNo++,this.mGetGrades()},currentChange:function(t){this.page.pageNo=t,this.mGetGrades()},shoolChange:function(){this.mGetGrades()}}},l=o,d=(a("c4bc"),a("2877")),c=Object(d["a"])(l,n,r,!1,null,null,null);e["default"]=c.exports},f3fe:function(t,e,a){"use strict";a.d(e,"c",(function(){return r})),a.d(e,"b",(function(){return s})),a.d(e,"d",(function(){return i})),a.d(e,"a",(function(){return o}));var n=a("b775");function r(t){return Object(n["a"])({url:"/admin/shool/getshools",method:"post",data:t})}function s(t){return Object(n["a"])({url:"/admin/shool/del",method:"post",data:t})}function i(t){return Object(n["a"])({url:"/admin/shool/update",method:"post",data:t})}function o(t){return Object(n["a"])({url:"/admin/shool/add",method:"post",data:t})}}}]);