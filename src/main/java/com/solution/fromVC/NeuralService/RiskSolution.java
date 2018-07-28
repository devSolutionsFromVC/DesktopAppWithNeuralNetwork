package com.solution.fromVC.NeuralService;


class RiskSolution {
    // 0.0<    <=0.250
    private static final String rs_decrease = "Понижение";  //зниження при высокой вероятности возникновения стоимости актива и убытков
    // 0.250<  <=0.500
    private static final String rs_adoption = "Принятие";  //прийняття при невысоком убытке и невысокой ценносте актива в диапазоне
    // 0.500<  <=0.750
    private static final String rs_avoidance = "Избежание"; //уникнення при высокой ценности актива и не высоком убытке
    // 0.750<  <=1.0
    private static final String rs_capsize = "Перебрасывание";   //перекидання при высоком убытке невысокой стоимоте актива

    static String riskResult(double[] solution){
        switch (transferSolution(solution)){
            case 1: return rs_decrease;
            case 2: return rs_adoption;
            case 3: return rs_avoidance;
            case 4: return rs_capsize;
            default: return "Неопределено";
        }
    }

    private static int transferSolution(double[] solution) {
        for (double aSolution : solution) {
            if (aSolution <= 0.250 & aSolution > 0) {
                return 1;
            } else if (aSolution <= 0.500 & aSolution > 0.250) {
                return 2;
            } else if (aSolution <= 0.750 & aSolution > 0.500) {
                return 3;
            } else if (aSolution <= 1.0 & aSolution > 0.750) {
                return 4;
            }
        }
        return 0;
    }


}
