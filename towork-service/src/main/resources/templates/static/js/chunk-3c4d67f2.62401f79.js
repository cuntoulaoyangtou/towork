(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-3c4d67f2"],{ac01:function(t,e,o){"use strict";o.r(e);var a=function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"app-container"},[o("el-row",{attrs:{gutter:24}},[o("el-col",{attrs:{span:10}},[o("el-card",{staticClass:"box-card"},[o("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[o("span",[t._v("添加学校")]),t._v(" "),o("el-button",{staticStyle:{float:"right",padding:"3px 0"},attrs:{type:"text"},on:{click:function(e){return t.resetForm()}}},[t._v("清空")])],1),t._v(" "),o("div",{staticClass:"text item"},[o("el-form",{ref:"shool",attrs:{"label-position":"top","label-width":"80px",model:t.shool}},[o("el-form-item",{attrs:{label:"学校名称"}},[o("el-input",{model:{value:t.shool.s_name,callback:function(e){t.$set(t.shool,"s_name",e)},expression:"shool.s_name"}})],1),t._v(" "),o("el-form-item",{attrs:{label:"经度"}},[o("el-input",{model:{value:t.shool.s_longitude,callback:function(e){t.$set(t.shool,"s_longitude",e)},expression:"shool.s_longitude"}})],1),t._v(" "),o("el-form-item",{attrs:{label:"纬度"}},[o("el-input",{model:{value:t.shool.s_latitude,callback:function(e){t.$set(t.shool,"s_latitude",e)},expression:"shool.s_latitude"}})],1),t._v(" "),o("el-button",{attrs:{type:"primary",loading:t.loading},on:{click:t.addAndUpdate}},[t._v(t._s(t.add?"添加":"更新"))])],1)],1)])],1),t._v(" "),o("el-col",{attrs:{span:14}},[o("el-card",{staticClass:"box-card"},[o("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[o("span",[t._v("所有学校")]),t._v(" "),o("el-button",{staticStyle:{float:"right",padding:"3px 0"},attrs:{type:"text"},on:{click:t.mGetShools}},[t._v("刷新")])],1),t._v(" "),o("div",{staticClass:"text item"},[o("el-table",{staticStyle:{width:"100%"},attrs:{data:t.shools.filter((function(e){return!t.search||e.s_name.toLowerCase().includes(t.search.toLowerCase())}))}},[o("el-table-column",{attrs:{label:"编号",prop:"s_id",width:"60"}}),t._v(" "),o("el-table-column",{attrs:{label:"学校名称",prop:"s_name"}}),t._v(" "),o("el-table-column",{attrs:{label:"经度",prop:"s_longitude"}}),t._v(" "),o("el-table-column",{attrs:{label:"纬度",prop:"s_latitude"}}),t._v(" "),o("el-table-column",{attrs:{align:"right",fixed:"right",width:"140"},scopedSlots:t._u([{key:"header",fn:function(e){return[o("el-input",{attrs:{size:"mini",placeholder:"输入关键字搜索"},model:{value:t.search,callback:function(e){t.search=e},expression:"search"}})]}},{key:"default",fn:function(e){return[o("el-button",{attrs:{size:"mini"},on:{click:function(o){return t.handleEdit(e.$index,e.row)}}},[t._v("编辑")]),t._v(" "),[o("el-popconfirm",{attrs:{title:"确定删除"+e.row.s_name+"吗？"},on:{onConfirm:function(o){return t.handleDelete(e.$index,e.row)}}},[o("el-button",{attrs:{slot:"reference",size:"mini",type:"danger"},slot:"reference"},[t._v("删除")])],1)]]}}])})],1),t._v(" "),o("el-pagination",{attrs:{background:"",layout:"prev, pager, next",total:t.page.total,"current-page":t.page.pageNo,"page-size":t.page.pageSize},on:{"prev-click":t.prevClick,"next-click":t.nextClick,"current-change":t.currentChange}})],1)])],1)],1)],1)},s=[],n=(o("ac6a"),o("f3fe")),l={name:"GetShool",data:function(){return{page:{pageNo:1,pageSize:10,s_name:null,total:0},shool:{s_id:null,s_name:"",s_longitude:"",s_latitude:""},shools:[],add:!0,loading:!1,search:""}},created:function(){this.mGetShools()},onload:function(){},mounted:function(){},methods:{handleEdit:function(t,e){this.shool=e,this.add=!1},handleDelete:function(t,e){this.mDelShool(e.s_id)},mGetShools:function(){var t=this;this.resetForm(),Object(n["c"])(this.page).then((function(e){t.shools.length>0&&t.shools.splice(0,t.shools.length),t.page.total=e.data.total,e.data.list.forEach((function(e){t.shools.push(e)}))})).catch((function(){}))},mAddShool:function(){var t=this;Object(n["a"])(this.shool).then((function(e){e.data&&"SUCCESS"===e.code&&(t.$message({message:"添加成功",type:"success"}),t.mGetShools())})).catch((function(){t.loading=!1}))},mUpdateShool:function(){var t=this;delete this.shool.grades,Object(n["d"])(this.shool).then((function(e){e.data&&"SUCCESS"===e.code&&(t.$message({message:"修改成功",type:"success"}),t.mGetShools())})).catch((function(){t.loading=!1}))},mDelShool:function(t){var e=this;Object(n["b"])({s_id:t}).then((function(t){t.data&&t.data>0&&(e.$message({message:"删除成功",type:"success"}),e.mGetShools())}))},addAndUpdate:function(){this.loading=!0,this.add?this.mAddShool():this.mUpdateShool()},resetForm:function(){this.add=!0,this.loading=!1,this.shool={s_id:null,s_name:"",s_longitude:"",s_latitude:""}},prevClick:function(){this.page.pageNo--,this.mGetShools()},nextClick:function(){this.page.pageNo++,this.mGetShools()},currentChange:function(t){this.page.pageNo=t,this.mGetShools()}}},i=l,c=o("2877"),r=Object(c["a"])(i,a,s,!1,null,null,null);e["default"]=r.exports},f3fe:function(t,e,o){"use strict";o.d(e,"c",(function(){return s})),o.d(e,"b",(function(){return n})),o.d(e,"d",(function(){return l})),o.d(e,"a",(function(){return i}));var a=o("b775");function s(t){return Object(a["a"])({url:"/admin/shool/getshools",method:"post",data:t})}function n(t){return Object(a["a"])({url:"/admin/shool/del",method:"post",data:t})}function l(t){return Object(a["a"])({url:"/admin/shool/update",method:"post",data:t})}function i(t){return Object(a["a"])({url:"/admin/shool/add",method:"post",data:t})}}}]);