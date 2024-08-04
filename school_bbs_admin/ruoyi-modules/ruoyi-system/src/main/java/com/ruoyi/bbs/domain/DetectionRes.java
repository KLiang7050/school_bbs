package com.ruoyi.bbs.domain;

import java.util.List;

public class DetectionRes {
    private String msg; // 返回对应的描述
    private int code; // 返回码，详见返回码说明
    private String taskNo; // 本次请求号
    private Data data;

    // getters and setters


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private String resultMsg; // result对应的描述
        private int result; // 审核结果类型
        private List<ResultItem> resultItems; // 不合规项，合规时不返回

        public String getResultMsg() {
            return resultMsg;
        }

        public void setResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public List<ResultItem> getResultItems() {
            return resultItems;
        }

        public void setResultItems(List<ResultItem> resultItems) {
            this.resultItems = resultItems;
        }

        public static class ResultItem {
            private int nonComplianceType; // 详见不合规类型
            private String msg; // 不合规描述
            private List<Hit> hits; // 命中的词

            // getters and setters


            public int getNonComplianceType() {
                return nonComplianceType;
            }

            public void setNonComplianceType(int nonComplianceType) {
                this.nonComplianceType = nonComplianceType;
            }

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public List<Hit> getHits() {
                return hits;
            }

            public void setHits(List<Hit> hits) {
                this.hits = hits;
            }

            public static class Hit {
                private String probability; // 不合规项置信度
                private List<String> words;
                private List<WordHitPosition> wordHitPositions; // 送检文本命中词库的详细信息

                public List<String> getWords() {
                    return words;
                }

                public void setWords(List<String> words) {
                    this.words = words;
                }

                public String getProbability() {
                    return probability;
                }

                public void setProbability(String probability) {
                    this.probability = probability;
                }

                public List<WordHitPosition> getWordHitPositions() {
                    return wordHitPositions;
                }

                public void setWordHitPositions(List<WordHitPosition> wordHitPositions) {
                    this.wordHitPositions = wordHitPositions;
                }

                public static class WordHitPosition {
                    private List<List<Integer>> positions; // 命中词在文本中的起始和结束位置
                    private String label; // 关键词命中的细分标签
                    private String keyword; // 命中的词

                    // getters and setters

                    public List<List<Integer>> getPositions() {
                        return positions;
                    }

                    public void setPositions(List<List<Integer>> positions) {
                        this.positions = positions;
                    }

                    public String getLabel() {
                        return label;
                    }

                    public void setLabel(String label) {
                        this.label = label;
                    }

                    public String getKeyword() {
                        return keyword;
                    }

                    public void setKeyword(String keyword) {
                        this.keyword = keyword;
                    }
                }
            }
        }
    }
}
