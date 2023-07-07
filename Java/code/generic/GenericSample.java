package generic;

public class GenericSample {
    public static void main(String[] args) {
        GenericSample sample = new GenericSample();
        sample.checkGenericDto();
    }

    public void checkGenericDto() {
        CastingGenericDto<String> dto1 = new CastingGenericDto<String>();
        CastingGenericDto<StringBuffer> dto2 = new CastingGenericDto<>();
        CastingGenericDto<StringBuilder> dto3 = new CastingGenericDto<>();
        dto1.setObject(new String());
        dto2.setObject(new StringBuffer());
        dto3.setObject(new StringBuilder());
        String object = dto1.getObject();
        StringBuffer object2 = dto2.getObject();
        StringBuilder object3 = dto3.getObject();
    }
}
