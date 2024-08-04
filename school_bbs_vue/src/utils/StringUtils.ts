import cheerio from 'cheerio';
// 将html转为字符串方法
// export const htmlToString = (htmlStr) => {
//     return htmlStr != null ? htmlStr.replace(/<\/?.+?\/?>|\r|\n|\s*/g, '') : ''
// }

export const htmlToString = (htmlStr: string): string => {
    if (!htmlStr) return '';
    const $ = cheerio.load(htmlStr);
    return $('body').text().trim();
  };