<template>
  <q-page
  class="bg-indigo-10"
  >
    <div class="row justify-center q-pt-lg">
      <div class="col-md-4 col-xs-8">
        <q-card flat>
          <q-card-section>
            <div class="text-h4 q-pb-md text-center "><b>ADMIN LOGIN</b></div>
            <q-form
      @submit="onSubmit"
      @reset="onReset"
      class="q-gutter-sm"
    >
       <q-input
        filled
        type="text"
        v-model="Username"
        label= "Masukkan Username Anda "
        lazy-rules
        :rules="[ val => val !== null && val !== '' || 'Wajib disi']"
      />

      <q-input
        filledl
        type="Password"
        v-model="Password"
        label="Masukkan Password Anda"
        lazy-rules
        :rules="[ val => val !== null && val !== '' || 'Password Tidak Boleh Kosong']"
        />

      <div>
        <q-btn label="Login" type="submit" color="primary"/>
      </div>
    </q-form>
                </q-card-section>
                </q-card>
           </div>
       </div>
    </q-page>
</template>

<script>
export default {
  data () {
    return {
      Username: '',
      Password: ''
    }
  },
  methods: {
    onSubmit () {
      this.$axios.post('login/login', {
        Username: this.Username,
        Password: this.Password
      }).then(res => {
        if (res.data.error) {
          this.showNotif(res.data.pesan, 'negative')
        } else {
          this.$router.push('/home')
        }
        this.Username = ''
        this.Password = ''
      })
    },
    showNotif (msg, color) {
      this.$q.notify({
        message: msg,
        color: 'color'
      })
    }
  }
}
</script>
