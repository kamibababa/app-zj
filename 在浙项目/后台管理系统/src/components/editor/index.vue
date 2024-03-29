<template>
  <div>
    <quilleditor
      v-model="content"
      ref="myTextEditor"
      :options="editorOption"
      @change="onChange"
    >
      <div :id="id" slot="toolbar">
        <select class="ql-size">
          <option value="small"></option>
          <!-- Note a missing, thus falsy value, is used to reset to default -->
          <option selected></option>
          <option value="large"></option>
          <option value="huge"></option>
        </select>
        <!-- Add subscript and superscript buttons -->
        <span class="ql-formats"
          ><button class="ql-script" value="sub"></button
        ></span>
        <span class="ql-formats"
          ><button class="ql-script" value="super"></button
        ></span>
        <span class="ql-formats"
          ><button type="button" class="ql-bold"></button
        ></span>
        <span class="ql-formats"
          ><button type="button" class="ql-italic"></button
        ></span>
        <span class="ql-formats"
          ><button type="button" class="ql-blockquote"></button
        ></span>
        <span class="ql-formats"
          ><button type="button" class="ql-list" value="ordered"></button
        ></span>
        <span class="ql-formats"
          ><button type="button" class="ql-list" value="bullet"></button
        ></span>
        <span class="ql-formats"
          ><button type="button" class="ql-link"></button
        ></span>
        <span class="ql-formats">
          <button type="button" @click="imgClick" style="outline: none">
            <svg viewBox="0 0 18 18">
              <rect class="ql-stroke" height="10" width="12" x="3" y="4"></rect>
              <circle class="ql-fill" cx="6" cy="7" r="1"></circle>
              <polyline
                class="ql-even ql-fill"
                points="5 12 5 11 7 9 8 10 11 7 13 9 13 12 5 12"
              ></polyline>
            </svg>
          </button>
        </span>
        <span class="ql-formats"
          ><button type="button" class="ql-video"></button
        ></span>
      </div>
    </quilleditor>
  </div>
</template>
<script>
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";

import { quillEditor } from "vue-quill-editor";
import Quill from "quill";
import ImageResize from "quill-image-resize-module";
import { ImageDrop } from "quill-image-drop-module";
import { ImageExtend } from "quill-image-paste-module";
Quill.register("modules/imageResize", ImageResize);
Quill.register("modules/imageDrop", ImageDrop);
Quill.register("modules/ImageExtend", ImageExtend);

import { uploadImg } from "@/api/uploadController";

export default {
  name: "v-editor",
  props: {
    value: {
      type: String,
    },
    /*上传图片的地址*/
    uploadUrl: {
      type: String,
      default: "/",
    },
    /*上传图片的file控件name*/
    fileName: {
      type: String,
      default: "file",
    },
    maxUploadSize: {
      type: Number,
      default: 1024 * 1024 * 1000,
    },
    id:{
        type:String
    }
  },
  data() {
    return {
      content: "",
      editorOption: {
        modules: {
          toolbar: '#'+this.id,
          //调整图片大小
          imageResize: {
            displayStyles: {
              backgroundColor: "black",
              border: "none",
              color: "white",
            },
          },
          // 拖拽上传
          imageDrop: true,
          // 截屏上传
          ImageExtend: {
            loading: true,
            name: "file",
            // 设置上传参数，因为我们要上传七牛，所以需要token
            change: (xhr, FormData) => {
              uploadImg(FormData).then((res) => {
                if (res.data) {
                  this.editor.insertEmbed(
                    this.editor.getSelection().index,
                    "image",
                    res.data.data
                  );
                }
              });
            },
            //此处修改路径
            action: "https://localhost:8081/oss/upload",
            response: (res) => {
              console.log("res", res);
            },
          },
        },
      },
    };
  },
  methods: {
    onChange() {
        this.$emit('val',this.content,this.id);
    },
    // 选择上传图片切换
    onFileChange(e) {
      var fileInput = e.target;
      //console.log('fileInput',fileInput)
      if (fileInput.files.length === 0) {
        return;
      }
      this.editor.focus();
      if (fileInput.files[0].size > this.maxUploadSize) {
        this.$alert("图片不能大于500KB", "图片尺寸过大", {
          confirmButtonText: "确定",
          type: "warning",
        });
      }
      var data = new FormData();
      data.append(this.fileName, fileInput.files[0]);
      uploadImg(data).then((res) => {
        if (res.data) {
          this.editor.insertEmbed(
            this.editor.getSelection().index,
            "image",
            res.data.data
          );
        }
      });
    },
    //点击上传图片按钮
    imgClick() {
      if (!this.uploadUrl) {
        console.log("no editor uploadUrl");
        return "";
      }
      // 内存创建input file
      var input = document.createElement("input");
      input.type = "file";
      input.name = this.fileName;
      input.accept = "image/jpeg,image/png,image/jpg,image/gif";
      input.onchange = this.onFileChange;
      input.click();
    },
  },
  computed: {
    editor() {
      return this.$refs.myTextEditor.quill;
    },
  },
  components: {
    quilleditor: quillEditor,
  },
  mounted() {
    this.content = this.value;
  },
  watch: {
    value(newVal, oldVal) {
      if (this.editor) {
        if (newVal !== this.content) {
          this.content = newVal;
        }

      }
    },
  },
};
</script>