<template>
  <el-form class="login-form" status-icon :rules="loginRules" ref="loginForm" :model="loginForm" label-width="0">
    <el-form-item prop="phone">
      <el-input size="small" @keyup.enter.native="handleLogin" v-model="loginForm.uEmail" auto-complete="off" placeholder="请输入邮箱">
        <i slot="prefix" class="icon-shouji"></i>
      </el-input>
    </el-form-item>
    <el-form-item prop="code">
      <el-input size="small" @keyup.enter.native="handleLogin" v-model="loginForm.code" auto-complete="off" placeholder="请输入验证码">
        <i slot="prefix" class="icon-yanzhengma yanzhengma" style=""></i>
        <template slot="append">
          <span @click="handleSend" class="msg-text" :class="[{display:msgKey}]">{{msgText}}</span>
        </template>
      </el-input>
    </el-form-item>
    <el-form-item>
      <el-button size="small" type="primary" @click.native.prevent="register" class="register">注册</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
const MSGINIT = '发送验证码'
// const MSGERROR = '验证码发送失败'
const MSGSCUCCESS = '${time}秒后重发'
const MSGTIME = 60
import { isvalidatemobile } from '@/utils/validate'
export default {
  name: 'codelogin',
  data() {
    const validatePhone = (rule, value, callback) => {
      if (isvalidatemobile(value)[0]) {
        callback(new Error(isvalidatemobile(value)[1]))
      } else {
        callback()
      }
    }
    const validateCode = (rule, value, callback) => {
      if (value.length !== 6) {
        callback(new Error('请输入6位数的验证码'))
      } else {
        callback()
      }
    }
    return {
      msgText: MSGINIT,
      msgTime: MSGTIME,
      msgKey: false,
      loginForm: {
        uEmail: '',
        code: ''
      },
      loginRules: {
        uEmail: [{ required: true, trigger: 'blur', validator: validatePhone }],
        code: [{ required: true, trigger: 'blur', validator: validateCode }]
      }
    }
  },
  created() {},
  mounted() {},
  computed: {
  },
  props: [],
  methods: {
    handleSend() {
      if (this.msgKey) return
      this.msgText = MSGSCUCCESS.replace('${time}', this.msgTime)
      this.msgKey = true
      const time = setInterval(() => {
        this.msgTime--
        this.msgText = MSGSCUCCESS.replace('${time}', this.msgTime)
        if (this.msgTime === 0) {
          this.msgTime = MSGTIME
          this.msgText = MSGINIT
          this.msgKey = false
          clearInterval(time)
        }
      }, 1000)
    },
    register() {
      
    }
  }
}
</script>

<style>
.yanzhengma{
  display:block;
  margin-top: 8px;
}
.msg-text {
  display: block;
  width: 60px;
  font-size: 12px;
  text-align: center;
  cursor: pointer;
}
.msg-text.display {
  color: #ccc;
}
</style>
