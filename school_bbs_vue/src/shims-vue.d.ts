declare module '*.vue' {
  import { ComponentOptions } from 'vue'
  const componentOptions: ComponentOptions
  export default componentOptions
}

import { SlateElement } from '@wangeditor/editor'

type ImageElement = SlateElement & {
    src: string
    alt: string
    url: string
    href: string
}